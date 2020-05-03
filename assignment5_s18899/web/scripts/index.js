function callback(response) {
	$("#date").html(response.date);
}

function ajax() {
    $.post("/date", callback, "json");
}

setInterval(ajax, 1000);