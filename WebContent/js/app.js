window.onresize = function(){
if(window.innerWidth >= 981){
		document.getElementById("side-menu").removeAttribute('style');
        document.getElementById("menu-btn").removeAttribute('style');
        document.getElementById("close-btn").removeAttribute('style');
	}
}

function openMenu() {
    document.getElementById("side-menu").style.display = "block";
    document.getElementById("menu-btn").style.display = "none";
    document.getElementById("close-btn").style.display = "block";
}

function closeMenu() {
    document.getElementById("side-menu").style.display = "none";
	document.getElementById("menu-btn").style.display = "block";
	document.getElementById("close-btn").style.display = "none";
}

//Animazione zoom hover product top
$('.effect').mouseleave(()=>{
	$(this).removeClass('effect');
})
