$(document).ready( function () {
    var table = $('#simpleRecords').DataTable({
        "sAjaxSource": "/base/simpleRecords",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "shortName" },
            { "mData": "recordName" }
        ],
        "responsive": "true",
        columnDefs:[
            { responsivePriority: 1, targets: 0 },
            { responsivePriority: 10001, targets: 2 },
            { responsivePriority: 2, targets: 1 }
        ]
    })
});