var button = document.getElementById("choose-all-users");

button.onclick = function() {
    var checkboxes = document.getElementsByClassName("toCheck");
    for(var i in checkboxes) {
        checkboxes[i].checked = true;
    }
};