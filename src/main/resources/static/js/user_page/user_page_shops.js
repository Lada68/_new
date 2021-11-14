
$(document).ready(function() {

    $('.table #editButton').on('click', function (event) {

        event.preventDefault();

        var href = $(this).attr('href');
        console.log($.get(href))


        $.get(href, function (shop) {
            shop=JSON.parse(shop);
            $(' #inputId').val(shop.id);
            $('#inputName1').val(shop.name);
            $('#inputCountry1').val(shop.location);
            $('#inputCity1').val(shop.city);
            $('#inputEmail1').val(shop.email);
            $('#inputPhone1').val(shop.phone);
            $(' #inputDescription1').val(shop.description);
        });
        $('#updateShopModal').modal();
    });

});

function showUserShops() {


        for (let shop of user.shops) {
            console.log(shop)

            let Shop = document.createElement('iframe')
            Shop.className = "DELSHOP"
            // Shop.src = "/market/" + shop.id
            Shop.src = "https://yandex.ru"
            Shop.height = "500px"
            Shop.width = "500px"
            // let newLi = document.createElement('li')
            // newLi.className = "DELSHOP"
            // newLi.href = "/market/" +

            document.querySelector('.usershops').appendChild(Shop)
        }

    }
