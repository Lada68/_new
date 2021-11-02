function showUserShops() {


    for(let shop of user.shops) {
        console.log(shop)

        let Shop = document.createElement('iframe')
        Shop.className = "DELSHOP"
        Shop.src = "/market/" + shop.id
        Shop.height = "500px"
        Shop.width = "500px"
        // let newLi = document.createElement('li')
        // newLi.className = "DELSHOP"
        // newLi.href = "/market/" +

        document.querySelector('.usershops').appendChild(Shop)
    }

}