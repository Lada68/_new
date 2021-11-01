function userProfile() {
    tempUser= JSON.parse(JSON.stringify(user));

    let userProfile = document.querySelectorAll('.profile')
    userProfile[0].value = user.firstName;
    userProfile[1].value = user.lastName;
    userProfile[2].value = user.email;
    userProfile[3].value = user.phone;
    userProfile[4].value = user.age;
    userProfile[5].value = user.birthday;
    if(user.gender === "MALE") userProfile[6].setAttribute("selected", "selected")
        else userProfile[7].setAttribute("selected", "selected")
    editAddresses()
}

async function handleClickSubmitProfile() {
    extractUser(document.querySelectorAll('.profile'))
    await loadFoto()
    updateUser()
    openShops()
}

//////////////// Extract user from the form
function extractUser(form) {
    tempUser.gender = "FEMALE"
    if(form[6].selected) tempUser.gender = "MALE"
    tempUser.firstName = form[0].value
    tempUser.lastName =  form[1].value
    tempUser.email =  form[2].value
    tempUser.phone =  form[3].value
    tempUser.age =  form[4].value
    tempUser.birthday =  form[5].value
}

/////////// Load new Foto
function loadFoto(){
    let fileInput = document.querySelector(".newfoto")
    if(fileInput.files[0] == undefined) return
    return new Promise((resolve, reject) => {
        let reader = new FileReader();
        reader.onload = () => {
            document.loadfoto.src = reader.result
            let res = reader.result.replace(/data:image.*,/,"")
            tempUser.images.push({id: null,url: "https://www.avito.st/stub_avatars/А/10_256x256.png", picture: res, isMain: false})
            resolve()
        }
        reader.onerror = reject
        reader.readAsDataURL(fileInput.files[0]);
    })
}

function fotoEdit() {
    const rowsToDelete = document.querySelectorAll('.DELFOTO')
    rowsToDelete.forEach(row => row.remove())

    let editFoto = document.querySelector('.editFotoModal')
    let i = 0;
    let isMain = ""
    for (let image of user.images) {
        src = "data:image/png;base64," + image.picture
        if (image.isMain === true) isMain = "checked"
        else isMain = ""

        let varHTML =
            " <tr class = \"DELFOTO\">\n" +
            "                                            <td>\n" +
            "                                                <img style=\"border-radius: 50%; max-width: 40px\" src=\"" + src + "\" class=\"img mx-2\" alt=\"\">\n" +
            "                                            </td>\n" +
            "                                            <td>\n" +
            "                                                <div class=\"form-check\" style=\"padding-left: 100px\">\n" +
            "                                                    <input class=\"isMain form-check-input\" name = \"radio\" type=\"radio\" id =\"radio[" + i + "]\" " + isMain + ">\n" +
            "                                                    <label class=\"form-check-label\" for=\"radio[" + i + "]\">\n" +
            "                                                        Сделать главной\n" +
            "                                                    </label>\n" +
            "                                                </div>\n" +
            "                                            </td>\n" +
            "                                            <td>\n" +
            "                                                <div class=\"form-check\" style=\"padding-left: 40px\">\n" +
            "                                                    <input class=\"toDelete form-check-input\" type=\"checkbox\" id =\"check[" + i + "]\" " + false + ">\n" +
            "                                                    <label class=\"form-check-label\" for=\"check[" + i + "]\">\n" +
            "                                                        Удалить\n" +
            "                                                    </label>\n" +
            "                                                </div>\n" +
            "                                            </td>\n" +
            "                                        </tr>"


        editFoto.insertAdjacentHTML('beforeend', varHTML);
        i++
    }
    console.log(editFoto)



}

function saveFoto() {
    let main = document.querySelectorAll('.isMain')
    let i = 0
    for (let isMain of main) {
        if(isMain.checked === true) tempUser.images[i].isMain = true
        else tempUser.images[i].isMain = false
        i++
    }

    let toDelete = document.querySelectorAll('.toDelete')
    i = 0
    for (let isDelete of toDelete) {
        if(isDelete.checked === true) {tempUser.images.splice(i,1); i--}
        i++
    }
    console.log(tempUser)
}

function editAddresses() {
    const rowsToDelete = document.querySelectorAll('.addr21addr22')
    rowsToDelete.forEach(row => row.remove())

    writeAddresses(user.address, "addr21", "addr22")
    let country = document.querySelectorAll('.countryaddr21addr22')
    for (let cntry of country) cntry.insertAdjacentHTML('afterend', "<input type=\"text\" >введите страну")
    let city = document.querySelectorAll('.cityaddr21addr22')
    for (let cty of city) cty.insertAdjacentHTML('afterend', "<input type=\"text\" >введите город")
    let street = document.querySelectorAll('.streetaddr21addr22')
    for (let strt of street) strt.insertAdjacentHTML('afterend', "<p><input type=\"text\" >введите улицу<input type=\"text\" >введите дом</p>")
}