const registerForwordButton = document.querySelectorAll(".account-button")[1];

registerForwordButton.onclick = () => {
    location.href = "/account/register";
}

const passwordfind = document.querySelector(".find-password-link");
const accountCurrent = document.querySelector(".account-current");
const accountPasswordForgot = document.querySelector(".account-password-forgot");

passwordfind.onclick = () => {
    accountCurrent.classList.add("account-invisible");
    accountPasswordForgot.classList.remove("account-invisible");
}