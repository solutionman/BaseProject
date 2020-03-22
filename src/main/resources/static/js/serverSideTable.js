$(document).ready( function () {
    var table = $('#simpleRecords').DataTable({
        "sAjaxSource": "/base/simpleRecords",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "recordName" },
            { "mData": "shortName" }
        ]
    })
});