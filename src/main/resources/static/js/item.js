setActiveMiniImg()
linkToReviews = document.querySelector('#toReviews')

$('.carousel').on('slid.bs.carousel', function () {
    document.querySelector('.mini-img-list .active').classList.remove('active')
    setActiveMiniImg()
})

function setActiveMiniImg() {
    document.querySelector(`#mini-img-${getActiveImg()}`).classList.add('active')
}

function getActiveImg() {
    return document.querySelector('.carousel-indicators .active').getAttribute('data-slide-to')
}

linkToReviews.addEventListener('click', evt => {
    evt.preventDefault()
    document.querySelector('#reviews').scrollIntoView({behavior: "smooth"})
})
