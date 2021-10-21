async function initUserPage() {
    let user = await getUserPrincipal()
    writeUserData(user)
}

function writeUserData(user) {

    userFoto(user.images)

//    document.user_foto.src = user.images.url   // картинка по URL
//    document.user_foto.src = "data:image/png;base64,"+user.images.picture  // картинка из файла
    let userData = document.querySelectorAll('.user_data')
    userData[0].innerHTML = user.username;
    userData[1].innerHTML = "Возраст: " + user.age;
    userData[2].innerHTML = "Пол: " + user.gender;

    writeAddresses(user.address)

    // userData[3].innerHTML = "Страна: " + user.address.country;
    // userData[4].innerHTML = "Город: " + user.address.city;
    // userData[5].innerHTML = "Улица: " + user.address.street + " дом: "+ user.address.house;

    userData[6].innerHTML = "Почта: " + user.email;
    userData[7].innerHTML = "Телефон: " + user.phone;
}

function userFoto(images) {
    let i = 0
    for (let image of images) {

        let newLi = document.createElement('li')
        newLi.setAttribute('data-target', "#carouselIndicators")
        newLi.setAttribute('data-slide-to', i.toString())
        document.querySelector(".carousel-indicators").appendChild(newLi)

        let img = document.createElement("img");
        img.className = "d-block w-100"
        img.style = "border-radius: 50%; border: solid black; max-width: 255px"
        img.alt = i + "slide"
        img.src = "data:image/png;base64," + image.picture

        let inner = document.createElement("div")
        inner.className = "carousel-item";
        if (i === 0) inner.className = "carousel-item active";
        inner.appendChild(img)

        document.querySelector('.carousel-inner').appendChild(inner)

        i++
    }
}

function writeAddresses (addresses) {

    let i=0
    for(let address of addresses) {
        let newLi = document.createElement('li')
        newLi.className = "nav-item"
        let newA = document.createElement('a')
        newA.className = "nav-link"
        if (i === 0) newA.className = "nav-link active"
        newA.setAttribute('data-toggle',"tab")
        newA.href ="#" + i
        newA.text = i.toString()
        newLi.appendChild(newA)
        document.querySelector('.nav-tabs').appendChild(newLi)

        let newDiv = document.createElement('div')
        newDiv.className = "tab-pane"
        if (i === 0) newDiv.className = "tab-pane active"
        newDiv.id = i.toString()
        let li1 = document.createElement('li')
        li1.innerHTML = "Страна: " + address.country;
        newDiv.appendChild(li1)
        let li2 = document.createElement('li')
        li2.innerHTML = "Город: " + address.city;
        newDiv.appendChild(li2)
        let li3 = document.createElement('li')
        li3.innerHTML = "Улица: " + address.street + " дом: "+ address.house;
        newDiv.appendChild(li3)
        document.querySelector('.tab-content').appendChild(newDiv)

        i++

        let x = document.querySelector('.col-md-8')
        console.log(x)
    }
}

initUserPage()