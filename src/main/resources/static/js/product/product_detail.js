window.onload = () => {
    LeftbarService.getInstance().loadLeftbar();

    new ProductDetail();
}       

class ProductApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new ProductApi();
        }
        return this.#instance;
    }
        
    getProductData() {
        let responseData = null;
        const url = location.href;
        const pdtId = url.substring(url.lastIndexOf("/") + 1);

        $.ajax({
            async: false,
            type: "get",
            url: "/api/product/" + pdtId,
            dataType: "json",
            success: response => {
                responseData = response.data;
            },
            error: error => {
                console.log(error);
            }
        });

        return responseData;

    }

    setLike(pdtId) {
        let likeCount = -1;
        
        $.ajax({
            async: false,
            type: "post",
            url: `/api/product/${pdtId}/like`,
            dataType: "json",
            success: response => {
                likeCount = response.data;
            },
            error: error => {
                console.log(error);
            }
        });

        return likeCount;
    }

    setDisLike(pdtId) {
        let likeCount = -1;
        
        $.ajax({
            async: false,
            type: "delete",
            url: `/api/product/${pdtId}/like`,
            dataType: "json",
            success: response => {
                likeCount = response.data;
            },
            error: error => {
                console.log(error);
            }
        });

        return likeCount;
    }
}

class ProductDetail {

    constructor() {
        const responseData = ProductApi.getInstance().getProductData();
        this.loadProductImgs(responseData);
        this.loadProductDetail(responseData);
        this.loadProductColors(responseData);
        this.loadProductSizes(responseData);
        this.loadProductLike(responseData);
    }

    loadProductImgs(responseData) {
        const productImages = document.querySelector(".product-images");
        productImages.innerHTML = ``;

        responseData.pdtImgs.forEach(img => {
            productImages.innerHTML += `
                <div class="product-image">
                    <img src="/image/product/${img}">
                </div>
            `;
        });
    }

    loadProductDetail(responseData) {
        document.querySelector(".product-title").textContent = responseData.pdtName;
        document.querySelector(".product-price").textContent = "₩ " + responseData.pdtPrice +"원";
        document.querySelector(".simple-info").innerHTML = responseData.pdtSimpleInfo;
        document.querySelector(".detail-info").innerHTML = `<strong>PRODUCT DETAILS</strong>
${responseData.pdtDetailInfo}`;
        document.querySelector(".option-info").innerHTML = responseData.pdtOptionInfo;
        document.querySelector(".management-info").innerHTML = responseData.pdtManagementInfo;
        document.querySelector(".shipping-info").innerHTML = responseData.pdtShippingInfo;
        
    }

    loadProductColors(responseData) {
        const productColors = document.querySelector(".product-colors");
        productColors.innerHTML = `<option value="">색상 선택</option>`;

        Object.keys(responseData.pdtColors).forEach(color => {
            productColors.innerHTML += `<option value="${color}">${color}</option>`;
        });
    }

    loadProductSizes(responseData) {
        const productColors = document.querySelector(".product-colors");
        const productSizes = document.querySelector(".product-sizes");
        productSizes.innerHTML = `<option value="">사이즈 선택</option>`;
        Object.entries(responseData.pdtColors).forEach(entry => {
            if(productColors.value == entry[0]) {
                entry[1].forEach(value => {
                    productSizes.innerHTML += `
                        <option for="product-size-${value.sizeName}" value="${value.pdtDtlId}" ${value.pdtStock == 0 ? 'class="no-stock"' : ''}>${value.sizeName}</option>
                    `;
                })
                
            }
        });

        this.addColorsSelectEvent(responseData);
    }

    addColorsSelectEvent(responseData) {
        const productColors = document.querySelector(".product-colors");
        productColors.onchange = () => {
            this.loadProductSizes(responseData);
        }
    }

    loadProductLike(responseData){
        const principal = PrincipalApi.getInstance().getPrincipal();

        const _likeButtons = document.querySelectorAll(".btns");
        const likeButtonsLength = _likeButtons == null ? 0 : _likeButtons.length;

        responseData.pdtLike.forEach((data, index) => {
            const likeButtons = document.querySelectorAll(".btns");
            if(principal == null){
                likeButtons[likeButtonsLength + index].innerHTML = `
                    <button type="submit" class="btn1">구매하기</button>
                    <button type="submit" class="btn2" disabled>찜하기</button>
                `
            }else{
                if(data.likeId != 0){
                    likeButtons[likeButtonsLength + index].innerHTML = `
                        <button type="submit" class="btn1">구매하기</button>
                        <button type="submit" class="btn2 like-buttons dislike-button">찜 하기</button>
                        
                    `
                }else{
                    likeButtons[likeButtonsLength + index].innerHTML = `
                        <button type="submit" class="btn1">구매하기</button>
                        <button type="submit" class="btn2 like-buttons like-button">찜 취소하기</button>
                    `
                }
            }
        });
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

    addClickEventLikeButtons() {
        const likeButtons = document.querySelectorAll(".like-buttons");
        const pdtIds = document.querySelectorAll(".btn2");
        const likeCounts = document.querySelectorAll(".like-count");

        likeButtons.forEach((button, index) => {
            button.onclick = () => {
                if(button.classList.contains("like-button")){
                    const likeCount = ProductApi.getInstance().setLike(pdtIds[index].value);
                    if(likeCount != -1){
                        likeCounts[index].textContent = likeCount;
                        button.classList.remove("like-button");
                        button.classList.add("dislike-button");
                        button.textContent.innerHTML = `
                            <i class="bi bi-heart-fill"></i>
                        `;
                    }
                    
                }else {
                    const likeCount = ProductApi.getInstance().setDisLike(pdtIds[index].value);
                    if(likeCount != -1){
                        likeCounts[index].textContent = likeCount;
                        button.classList.remove("dislike-button");
                        button.classList.add("like-button");
                        button.textContent  = `
                            <i class="bi bi-heart"></i>
                        `;
                    }
                }
            }
        });
    }

}