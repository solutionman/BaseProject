$(document).ready( function () {
    var table = $('#testRecords').DataTable({
        "sAjaxSource": "/base/testRecords",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "username"},
            { "mData": "firstname" },
            { "mData": "lastname" }
        ]
    })
});