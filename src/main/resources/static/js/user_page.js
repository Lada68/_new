async function initUserPage() {
    let user = await getUserPrincipal()
    console.log(user)
    writeUserData(user)
}

function writeUserData(user) {
    let userData = document.querySelectorAll('.user_data')
    console.log(user)
//    document.user_foto.src = user.images.url   // картинка по URL
    document.user_foto.src = "data:image/png;base64,"+user.images.picture  // картинка из файла
    userData[0].innerHTML = user.username;
    userData[1].innerHTML = "Возраст: " + user.age;
    userData[2].innerHTML = "Пол: " + user.gender;
    userData[3].innerHTML = "Страна: " + user.address.country;
    userData[4].innerHTML = "Город: " + user.address.city;
    userData[5].innerHTML = "Улица: " + user.address.street;
    userData[6].innerHTML = "Почта: " + user.email;
    userData[7].innerHTML = "Телефон: " + user.phone;
}


initUserPage()