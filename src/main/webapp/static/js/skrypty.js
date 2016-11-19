var n = 0;
$("#login-form").submit(function (event) {
    if (n === 0) {
        if ($("#inputEmail").val() == '') {
            $("#inputEmail").parent().parent().addClass("has-error");
            $("#loginError").show();
            return false;
        }
        else {

            $("#log1").hide(500, function () {
                $.ajax({
                    url: "/MiniBank-1.0.0/getImageToken/"+$("#inputEmail").val(),
                    context: document.body
                }).done(function( data ) {
                    $( "#image-token" ).attr("src", "data:image/jpeg;base64,"+data);
                });
                $("#log2").show(500);
            });
            n = n + 1;
            return false;
        }
    } else {
        if ($("#inputPassword").val() == '') {
            $("#inputPassword").parent().parent().addClass("has-error");
            $("#passwordError").show();
            return false;
        }
        else {
            return true;
        }
    }
});


$("#resetLogin").click(function (event) {
    $("#log2").hide(500, function () {
        $("#log1").show(500);
        $("#inputEmail").parent().parent().removeClass("has-error");
        $("#inputPassword").parent().parent().removeClass("has-error");
        $("#loginError").hide();
        $("#passwordError").hide();
    });
    n = 0;
});
