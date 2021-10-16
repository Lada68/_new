async function initUserPage() {
    let user = await getUserPrincipal()
    writeUserData(user)
}

function writeUserData(user) {
    let userData = document.querySelectorAll('.user_data')
    document.user_foto.src = user.images;
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