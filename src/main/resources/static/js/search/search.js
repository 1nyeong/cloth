window.onload = () => {
    LeftbarService.getInstance().loadLeftbar();

    SearchService.getInstance().loadSearchProduct();
    
    ComponentEvent.getInstance().addClickEventSearchButton();
    
    SearchService.getInstance().onLoadSearch();
}

const searchObj = {
    page: 1,
    searchValue: null,
    count: 15
}

class SearchApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new SearchApi();
        }
        return this.#instance;
    }

    getTotalCount() {
        let responseData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "/api/search/totalcount",
            data: searchObj,
            dataType: "json",
            success: response => {
                responseData = response.data;
            },
            error: error => {
                console.log(error);
            }
        })

        return responseData;
    }


    searchProduct() {
        let responseData = null;

        $.ajax({
            async: false,
            type: "get",
            url: "/api/search",
            data: searchObj,
            dataType: "json",
            success: response => {
                responseData = response.data;
            },
            error: error => {
                console.log(error);
            }
        })
        return responseData
    }
}

class SearchService {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new SearchService();
        }
        return this.#instance;
    }

    onLoadSearch() {
        const URLSearch = new URLSearchParams(location.search);
        if(URLSearch.has("searchValue")){
            const searchValue = URLSearch.get("searchValue");
            if(searchValue == "") {
                return;
            }
            const searchInput = document.querySelector(".search-input");
            searchInput.value = searchValue;
            const searchButton = document.querySelector(".search-button");
            searchButton.click();
        }
    }
    
    clearBookList() {
        const contentFlex = document.querySelector(".collection-products");
        contentFlex.innerHTML = "";
    }


    loadSearchProduct() {
        const responseData = SearchApi.getInstance().searchProduct(); 
        const collectionProducts = document.querySelector(".collection-products");

        responseData.forEach(data => {
            collectionProducts.innerHTML += `
                <li class="collection-product">
                    <div class="product-img">
                        <img src="/image/product/${data.saveName != null ? data.saveName : "noimage.png"}">
                    </div>
                    <hr class="product-hr">
                    <div class="product-name">
                        ${data.productName}
                    </div>
                    <div class="product-price">
                        ${data.productPrice}원
                    </div>
                    <div class="product-simple">
                        ${data.productSimpleInfo}
                    </div>
                </li>
            `;
        })
    }

}

class ComponentEvent {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ComponentEvent();
        }
        return this.#instance;
    }

    // addClickEventSearchButton() {
    //     const searchButton = document.querySelector(".search-button");
    //     const searchInput = document.querySelector(".search-input");
    //     searchButton.onclick = () => {
    //         location.href = `/search?searchValue=${searchInput.value}`;
    //     }

    //     searchInput.onkeyup = () => {
    //         if(window.event.keyCode == 13) {
    //             searchButton.click();
    //         }
    //     }
    // }

    addClickEventSearchButton() {
        const searchButton = document.querySelector(".search-button");
        const searchInput = document.querySelector(".search-input");

        searchButton.onclick = () => {
            searchObj.searchValue = searchInput.value;
            searchObj.page = 1;
            searchObj.count = 10;
            window.scrollTo(0, 0);
            SearchService.getInstance().clearProductList();
            SearchService.getInstance().loadSearchProduct();
        }

        searchInput.onkeyup = () => {
            if(window.event.keyCode == 13) {
                searchButton.click();
            }
        }
    }

}