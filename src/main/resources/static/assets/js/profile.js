const open = document.querySelector("#open");

const close = document.querySelector("#close");

const modal_box = document.querySelector("#modal_box");

open.addEventListener("click",() => {
    modal_box.classList.add("active");
})

close.addEventListener("click",()=> {
    modal_box.classList.remove("active");
} )