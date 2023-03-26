window.onload = () => {
    LeftbarService.getInstance().loadLeftbar();
    
    CollectionsService.getInstance().loadCollections();
    PageScroll.getInstance().addScrollPagingEvent();
    
    ComponentEvent.getInstance().addClickEventSearchButton();

    SearchService.getInstance().onLoadSearch();
}

const searchObj = {
    page: 1,
    searchValue: null
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


class CollectionsApi {
  static #instance = null;

  static getInstance() {
      if(this.#instance == null) {
          this.#instance = new CollectionsApi();
      }
      return this.#instance;
  }

  getCollections(page) {
      let responseData = null;

      const url = location.href;
      const category = url.substring(url.lastIndexOf("/") + 1);

      $.ajax({
          async: false,
          type: "get",
          url: "/api/" + category,
          data: {
              "page": page
          },
          dataType: "json",
          success: (response) => {
              responseData = response.data;
          },
          error: (error) => {
              console.log(error);
          }
      });

      return responseData;

  }
}

class PageScroll {
  static #instance = null;

  static getInstance() {
      if(this.#instance == null) {
          this.#instance = new PageScroll();
      }
      return this.#instance;
  }
   
  addScrollPagingEvent() {
      const html = document.querySelector("html");
      const body = document.querySelector("body");

      body.onscroll = () => {
          // console.log("문서 전체 높이: " + body.offsetHeight);
          // console.log("눈에 보이는 영역 높이: " + html.clientHeight);
          // console.log("스크롤의 상단 위치: " + html.scrollTop);
          let scrollStatus = body.offsetHeight - html.clientHeight - html.scrollTop;
          if(scrollStatus > -50 && scrollStatus < 50) {
              const nowPage = CollectionsService.getInstance().collectionsEntity.page;
              CollectionsService.getInstance().collectionsEntity.page = Number(nowPage) + 1;
              CollectionsService.getInstance().loadCollections();
          }

      }
  }

}

class CollectionsService {
  static #instance = null;

  static getInstance() {
      if(this.#instance == null) {
          this.#instance = new CollectionsService();
      }
      return this.#instance;    
  }

  pdtIdList = null;

  collectionsEntity = {
      page: 1,
      totalCount: 0,
      maxPage: 0
  }

  constructor() {
      this.pdtIdList = new Array();
  }
 

  loadCollections() {
      if(this.collectionsEntity.page == 1 || this.collectionsEntity.page < Number(this.collectionsEntity.maxPage) + 1) {
          const responseData = CollectionsApi.getInstance().getCollections(this.collectionsEntity.page);
          console.log(responseData);
          if(responseData.length > 0) {
              this.collectionsEntity.totalCount = responseData[0].productTotalCount;
              this.collectionsEntity.maxPage = responseData[0].productTotalCount % 16 == 0 
                                              ? responseData[0].productTotalCount / 16
                                              : Math.floor(responseData[0].productTotalCount / 16) + 1;
              this.getCollections(responseData);
          }
      }
  }

  getCollections(responseData) {
      const collectionProducts = document.querySelector(".collection-products");

      responseData.forEach(product => {
          this.pdtIdList.push(product.productId);
          collectionProducts.innerHTML += `
            <li class="collection-product">
                <div class="product-img">
                    <img src="/image/product/${product.mainImg}">
                </div>
                <hr class="product-hr">
                <div class="product-name">
                    ${product.productName}
                </div>
                <div class="product-price">
                    ${product.productPrice}원
                </div>
                <div class="product-simple">
                    ${product.productSimpleInfo}
                </div>
            </li>
          `;
      });

      this.addProductListEvent();
  }

  addProductListEvent() {
      const collectionProducts = document.querySelectorAll(".collection-product");

      collectionProducts.forEach((product, index) => {
          product.onclick = () => {
              location.href = "/product/" + this.pdtIdList[index];
          }
      });

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

    pdtIdList = null;

    collectionsEntity = {
        page: 1,
        totalCount: 0,
        maxPage: 0
    }
  
    constructor() {
        this.pdtIdList = new Array();
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

    loadSearchProduct() {
        const responseData = SearchApi.getInstance().searchProduct();
        const collectionProducts = document.querySelector(".collection-products");

        console.log(responseData)
        responseData.forEach(data => {
            this.pdtIdList.push(data.id);
            collectionProducts.innerHTML += `
                <li class="collection-product">
                    <div class="product-img">
                        <img src="/image/product/${data.save_name}">
                    </div>
                    <hr class="product-hr">
                    <div class="product-name">
                        ${data.pdt_name}
                    </div>
                    <div class="product-price">
                        ${data.pdt_price}원
                    </div>
                    <div class="product-simple">
                        ${data.pdt_simple_info}
                    </div>
                </li>
            `;
        });

        this.addClickEventSearchProduct();
    }

    addClickEventSearchProduct() {
        const collectionProducts = document.querySelectorAll(".collection-product");

        collectionProducts.forEach((product, index) => {
            product.onclick = () => {
                location.href = "/product/" + this.pdtIdList[index];
            }
        });
  
    }

    clearProductList() {
        const collectionProducts = document.querySelector(".collection-products");
        collectionProducts.innerHTML = "";
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

    addClickEventSearchButton() {
        const searchInput = document.querySelector(".search-input");
        const searchButton = document.querySelector(".search-button");

        searchButton.onclick = () => {
            location.href = `/search?searchValue=${searchInput.value}`;
            searchObj.searchValue = searchInput.value;
            searchObj.page = 1;
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