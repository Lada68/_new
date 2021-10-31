function userProfile() {
    let userProfile = document.querySelectorAll('.profile')
    userProfile[0].value = user.firstName;
    userProfile[1].value = user.lastName;
    userProfile[2].value = user.email;
    userProfile[3].value = user.phone;
    userProfile[4].value = user.age;
    userProfile[5].balue = user.birthday;
    if(user.gender === "MALE") userProfile[6].setAttribute("selected", "selected")
        else userProfile[7].setAttribute("selected", "selected")
}

async function handleClickSubmitProfile() {
    extractUser(document.querySelectorAll('.profile'))
    await loadFoto()
    updateUser()
    openShops()
}

//////////////// Extract user from the form
function extractUser(form) {
    tempUser= JSON.parse(JSON.stringify(user));
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