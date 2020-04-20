$(document).ready( function () {
    var table = $('#testRecords').DataTable({
        "sAjaxSource": "/base/testRecords",
        "sAjaxDataProp": "",
        "processing": false,
        "serverSide": false,
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "username"},
            { "mData": "firstname" },
            { "mData": "lastname" }
        ]
    })
});