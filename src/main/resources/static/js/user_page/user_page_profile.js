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

// document.querySelector('.save').onclick = handleClickSubmit1

function handleClickSubmitProfile() {
    extractUser(document.querySelectorAll('.profile'))
    console.log("extract")
    console.log(user)
    console.log(tempUser)
    loadFoto()
    console.log("loadfoto")
    user= JSON.parse(JSON.stringify(tempUser));
    console.log(user)
    console.log(tempUser)
    updateUser()
    console.log("updateuser")
    console.log(user)
    console.log(tempUser)
}

//////////////// Extract user from the form
function extractUser(form) {
    tempUser= JSON.parse(JSON.stringify(user));
    console.log("beforextract")
    console.log(user)
    console.log(tempUser)
    tempUser.gender = "FEMALE"
    if(form[6].selected) tempUser.gender = "MALE"
    tempUser.firstName = form[0].value
    tempUser.lastName =  form[1].value
    tempUser.email =  form[2].value
    tempUser.phone =  form[3].value
    tempUser.age =  form[4].value
    tempUser.birthday =  form[5].value
    console.log("afterextract")
    console.log(user)
    console.log(tempUser)
}

function loadFoto() {
    // let file = document.querySelector(".newfoto").files[0]
    // console.log(file)
    // document.loadfoto.src = URL.createObjectURL(file);
    // console.log(URL.createObjectURL(file))

    var fileInput = document.querySelector(".newfoto");

    var reader = new FileReader();
    reader.readAsDataURL(fileInput.files[0]);

    reader.onload = function () {
        document.loadfoto.src = reader.result
        let res = reader.result.replace(/data:image.*,/,"")
  //      let length = tempUser.images.push({id: 100,url: "https://www.avito.st/stub_avatars/А/10_256x256.png", picture: res, isMain: false})
        let length1 = tempUser.images.push({id: 100,url: "https://www.avito.st/stub_avatars/А/10_256x256.png", picture: user.images[0].picture, isMain: false})
        console.log("length")
        console.log(length)
        console.log(length1)
    };
    reader.onerror = function (error) {
        console.log('Error: ', error);
    };


    //   let pic = URL.createObjectURL(file).replace(/^data:image\/(png|jpg);base64,/, "");
 //    console.log(file.readAsArrayBuffer())
 //    tempUser.images.push({id: null,url: null, picture: file.readAsArrayBuffer(), isMain: false})
}
