$(function () {
    $("#completion").dialog({
        modal: !0, dialogClass: "no-close", buttons: {
            Close: function () {
                $(this).dialog("close"), $(location).prop("href", "?action=menu")
            }
        }
    })
});