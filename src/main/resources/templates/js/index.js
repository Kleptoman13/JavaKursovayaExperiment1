const $list = document.querySelector('.list');
const $functions = document.querySelector('.functions');
const $liveSearchNumber = document.querySelector('.byNumber');
const $liveSearchName = document.querySelector('.byName');
const $liveSearchField = document.querySelector('.liveSearchField');
const $searchByText = document.getElementById('liveSearch');
const $searchByFreeNumber = document.querySelector('.byFreeNumber');
// const $isFree = document.querySelector('.freenumber');
const $bg_assignment = document.querySelector('.bg_assign');
const $assignment = document.querySelector('.assign');
const $list_of_ats = document.querySelector('.list_of_ats');
const $choosednumber = document.querySelector('.choosed_number');
const $asBtn_save = document.querySelector('.asBtn_save');
const $asBtn_cancel = document.querySelector('.asBtn_cancel');
const $bg_addAts = document.querySelector('.bg_addAts');
const $bg_delAts = document.querySelector('.bg_delAts');
const $addBtn = document.querySelector('.addBtn');
const $addAts = document.querySelector('.addAts');
const $addBtn_save = document.querySelector('.addBtn_save');
const $addBtn_cancel = document.querySelector('.addBtn_cancel');
const $delBtn = document.querySelector('.delBtn');
const $delAts = document.querySelector('.delAts');
const $delBtn_save = document.querySelector('.delBtn_save');
const $delBtn_cancel = document.querySelector('.delBtn_cancel');

const $addNumber = document.querySelector('.addNumber');
const $addName = document.querySelector('.addName');
const $addSurname = document.querySelector('.addSurname');
const $addLastName = document.querySelector('.addLastname');
const $addAddres = document.querySelector('.addAddres');
const $addMobileNumber = document.querySelector('.addMobileNumber');
const $addEmail = document.querySelector('.addEmail');
const $delNumber = document.querySelector('.delNumber');

let filterdAts;

let ats = null;

let isActive = 0;

//Генерация списка
function templateGenerator(list, isActive)
{
    let temp = '';
    if(list.length && isActive == 0)
    {
        for(let i = 0; i < list.length; i++)
        {
            if(list[i].isFreeNumber == 1)
            {
                temp += '<div class="number_info number" data-index="'+ i +'"><p class="number_infoP" data-index="'+ i +'">' + list[i].number + '<br>' + list[i].name + '<br>' + list[i].surname + '<br>' + list[i].lastname + '<br>' + list[i].addres + '<br>' + list[i].mobilenumber + '<br>' + list[i].email + '</p></div>'
            }
            else{
                temp += '<div class="number_info1 number" data-index="'+ i +'"><p class="number_infoP1" data-index="'+ i +'">Свободный номер<br>' + list[i].number + '</p></div>'
            }
        }
    }
    if (list.length && isActive == 1)
    {
        for(let i = 0; i < list.length; i++)
        {
            if(list[i].isFreeNumber == 0)
            {
                temp += '<div class="number_info1 number" data-index="'+ i +'"><p class="number_infoP1" data-index="'+ i +'">Свободный номер<br>' + list[i].number + '</p></div>'
            }
        }
    }
    if (list.length == 0)
    {
        temp += '<h1 class="NF">Ats not found!</h1>';
    }

    $list.innerHTML = temp;
}

//Генерация списка в модальном окне
function assignGeneretor(list)
{
    let temp = '';
    if(list.length)
    {
        for(let i = 0; i < list.length; i++)
        {
            if(list[i].isFreeNumber == 1)
            {
                temp += '<div class="number_info number" data-index="'+ i +'"><p class="number_infoP free_numberP" data-index="'+ i +'">' + list[i].number + '<br>' + list[i].name + '<br>' + list[i].surname + '<br>' + list[i].lastname + '<br>' + list[i].addres + '<br>' + list[i].mobilenumber + '<br>' + list[i].email + '</p></div>'
            }
        }
    }
    $list_of_ats.innerHTML = temp;
}

//Считование API
function GetInfo()
{
    fetch('http://localhost:8080/back?info')
    .then(function (response)
    {
        return response.json();
    })
    .then(function (data)
    {
        ats = data.atsList;
        templateGenerator(data.atsList, isActive);
        assignGeneretor(data.atsList);
    });
};

GetInfo();

let selectedBannerNumberIndex = 0;
let selectedBannerNumber = 0;

//открытие модального окна для присвоения номера
$list.addEventListener('dblclick', function(e)
{
        if (e.target.classList.contains('number_info1') || e.target.classList.contains('number_infoP1'))
        {
            selectedBannerNumberIndex = e.target.getAttribute('data-index');
            selectedBannerNumber = ats[selectedBannerNumberIndex].number;
            console.log(selectedBannerNumberIndex);
            console.log(selectedBannerNumber);
            $choosednumber.innerHTML =  '<h2>Выбранный номер: </h2>' + selectedBannerNumber;
            $bg_assignment.style.opacity = "1";
            $bg_assignment.style.visibility = "visible";
            $assignment.style.opacity = "1";
            $assignment.style.visibility = "visible";
        }
})



let tempSelectedBanner = 0;
$list_of_ats.addEventListener('click', function(e)
{
        if (e.target.classList.contains('number_info') || e.target.classList.contains('number_infoP'))
        {
            tempSelectedBanner = e.target.getAttribute('data-index');
            console.log(tempSelectedBanner);
            
        }
})

function isDSurnameCorrect() {
    isCorrect = true;
    if (($dSurname.value == ' ') || (!(/^[A-Za-z]+$/i).test($dSurname.value))) {
        isCorrect = false;
    }
    return isCorrect;
}


// Присвоение номера
$asBtn_save.addEventListener('click', function(e)
{

    let asNumber = 
    {
        selected_number: selectedBannerNumber,
        selected_banner: parseInt(tempSelectedBanner, 10),
        selected_numberI: parseInt(selectedBannerNumberIndex, 10)
    }
    console.log(asNumber)
    fetch("http://localhost:8080/back?addNumber", {
        method: 'POST',
        body: JSON.stringify(asNumber),
    })
    .then(function (response)
    {
        return response.json();
    })
    .then(function (data)
    {
        console.log(data);
    })
    
    
})

//Отмена присвоения
$asBtn_cancel.addEventListener('click', function()
{
    $bg_assignment.style.opacity = "0";
    $bg_assignment.style.visibility = "hidden";
    $assignment.style.opacity = "0";
    $assignment.style.visibility = "hidden";
})


$addBtn.addEventListener('click', function()
{
    $bg_addAts.style.opacity = "1";
    $bg_addAts.style.visibility = "visible";
    $addAts.style.opacity = "1";
    $addAts.style.visibility = "visible";
})

//Добавление АТС
$addBtn_save.addEventListener('click', function()
{
    let addAts = {
        number:$addNumber.value,
        isFreeNumber:1,
        name:$addName.value,
        surname:$addSurname.value,
        lastname:$addLastName.value,
        addres:$addAddres.value,
        mobilenumber:$addMobileNumber.value,
        email:$addEmail.value
    }
    console.log(addAts);
    fetch("http://localhost:8080/back?addAts", {
        method: 'POST',
        body: JSON.stringify(addAts),
    })
    .then(function(response)
    {
        return response.json();
    })

    $bg_addAts.style.opacity = "0";
    $bg_addAts.style.visibility = "hidden";
    $addAts.style.opacity = "0";
    $addAts.style.visibility = "hidden";
})

//Открытие окна для удаления АТС
$delBtn.addEventListener('click', function(){
    $bg_delAts.style.opacity = "1";
    $bg_delAts.style.visibility = "visible";
    $delAts.style.opacity = "1";
    $delAts.style.visibility = "visible";
})

$delBtn_save.addEventListener('click', function(){
    let deleteByNumber = $delNumber.value;
    fetch("http://localhost:8080/back?deleteAts", {
        method: 'POST',
        body: deleteByNumber,
    }).then(response =>
        response.json().then(data => ({
            data: data,
            status: response.status
        })).then(res => {
            if (JSON.stringify(res.data) == '{"Deletion":"Deleted"}') {
                alert("Deleted!")
            } else {
                alert("Try again!")
            }
        }));
    alert('Successful!');
})

// Отмена удаления
$delBtn_cancel.addEventListener('click', function()
{
    $bg_delAts.style.opacity = "0";
    $bg_delAts.style.visibility = "hidden";
    $delAts.style.opacity = "0";
    $delAts.style.visibility = "hidden";
})

// Отмена добавления
$addBtn_cancel.addEventListener('click', function()
{
    $bg_addAts.style.opacity = "0";
    $bg_addAts.style.visibility = "hidden";
    $addAts.style.opacity = "0";
    $addAts.style.visibility = "hidden";
})


// Поиск по номеру
$liveSearchNumber.addEventListener('input', function() {
    let query = this.value.toString().toLowerCase();
    let searchingValue = "number";
    console.log(query);

    let filterdAts = ats.filter(function (el){
        return ~el[searchingValue].toString().toLowerCase().indexOf(query);
    })

    templateGenerator(filterdAts, isActive);
})

// Поиск по номеру телефона или по имени
$searchByText.addEventListener('change', function () {
    let searchingValue = $searchByText.elements["liveSearch"].value;
    $liveSearchName.addEventListener('input', function () {
        let query = this.value.toString().toLowerCase();
        let filterdAts = ats.filter(function (el) {
            return ~el[searchingValue].toString().toLowerCase().indexOf(query);
        });
        templateGenerator(filterdAts, isActive);
    });
});

//Выдача списка свободных номеров
function checkFree()
{
    if($searchByFreeNumber.checked)
    {
        isActive = 1;
        $liveSearchName.style.visibility = "hidden"
        $liveSearchField.style.visibility = "hidden"
        fetch('http://localhost:8080/back?info')
        .then(function (response)
        {
            return response.json();
        })
        .then(function (data)
        {
            ats = data.atsList;
            templateGenerator(data.atsList, isActive);
        });
    }
    else{
        isActive = 0;
        $liveSearchName.style.visibility = "visible"
        $liveSearchField.style.visibility = "visible"
        fetch('http://localhost:8080/back?info')
        .then(function (response)
        {
            return response.json();
        })
        .then(function (data)
        {
            ats = data.atsList;
            templateGenerator(data.atsList, isActive);
        });
    }


    
}