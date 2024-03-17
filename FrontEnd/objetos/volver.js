function volverUltimaPagina() {
    var ultimaPaginaVisitada = localStorage.getItem('ultimaPaginaVisitada');
    
    if (ultimaPaginaVisitada) {
        window.location.href = ultimaPaginaVisitada;
    } else {
        window.location.href = 'index.html';
    }
}
