class LeftbarService {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new LeftbarService();
        }
        return this.#instance;
    }

    loadLeftbar(){
        const leftbarContainer = document.querySelector(".leftbar");
        const principal = PrincipalApi.getInstance().getPrincipal();

        leftbarContainer.innerHTML = `
            <div class="top_logo">
                <a>LOGO</a> 
            </div>
            <div class="menu_top">
                ${principal == null
                    ? `
                        <a href="/login" class="login">LOGIN</a>
                        <a href="/register" class="register">JOIN US</a>
                        <a href="/notice" class="leftbar-notice">NOTICE</a>
                        <a href="/boardlist" class="boardlist">Q&A</a>
                    `
                    : `
                    <a href="mypage" class="mypage">${principal.user.name}</a>
                    <a href="logout" class="logout">LOGOUT</a>
                    <a href="/notice" class="leftbar-notice">NOTICE</a>
                    <a href="/boardlist" class="boardlist">Q&A</a>
                    `
                }
            </div>
            <div class="search-container">
                <input type="text" name="search">
                <button type="submit"><i class="fa fa-search"></i></button>
            </div>
            <div class="main_menu">
                <ul>
                    <li><a href="/all" class="all">ALL</a></li>
                    <li><a href="/outer" class="outer">OUTER</a></li>
                    <li><a href="/top" class="top">TOP</a></li>
                    <li><a href="/pants" class="pants">PANTS</a></li>
                    <li><a href="/shirts" class="shirts">SHIRTS</a></li>
                    <li><a href="/bag&shoes" class="bag&shoes">BAG & SHOES</a></li>
                    <li><a href="/acc" class="acc">ACC</a></li>     
                </ul>
            </div>
            <div class="leftbar_info">
                <ul class="leftbar_info_top">
                    <li>T.051-0000-0000</li>
                    <ol>MONDAY TO FRIDAY AM. 11:00 - PM 05:00</a></ol>
                    <ol>SAT.SUN.HOLIDAY OFF</a></ol>
                </ul>
                <ul class="leftbar_info_bottom">
                    <li>BANK INFO</li>
                    <ol>은행 123456-12-12345</ol>
                    <ol>주식회사 LOGO</ol>
                </ul>
            </div>

        `;
    }

}