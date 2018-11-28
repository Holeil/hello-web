var elem = document.getElementById("user-info");

elem.onclick = function() {
    this.hidden = true;

    var input = document.getElementById("input-user-info");
    var send = document.getElementById("send-info");

    send.hidden = false;

    input.focus();

    input.onblur = function() {
        send.submit();
    };
}