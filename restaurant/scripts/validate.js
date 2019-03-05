
function validateItems() {
    var name = document.forms["contact"]["name"].value;
    var email = document.forms["contact"]["email"].value;
    var phone = document.forms["contact"]["phone"].value;
    var info = document.forms["contact"]["info"].value;
    if (name=="" || email=="" || phone=="" || info=="") {
        alert("Please fill out all fields.");
    } else {
        alert("The information you have given is valid, we will get back to your request as soon as possible.");
    }
}
