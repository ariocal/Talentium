
/*Comportamiento navToggle */
const navToggle = document.querySelector(".nav-toggle");
const navMenu = document.querySelector(".nav-menu");
const lateral = document.querySelector(".aside");
const perfil = document.querySelector(".header_y_container")


navToggle.addEventListener('click', ()=>{
    perfil.classList.toggle('_visible');
    lateral.classList.toggle('aside_visible');
    navMenu.classList.toggle('nav-menu_visible');
    if(navMenu.classList.contains("nav-menu_visible")){
        navToggle.setAttribute("aria-label","Cerrar Menú");
    }else{
        navToggle.setAttribute("aria-label","Abrir Menú");
    }
});