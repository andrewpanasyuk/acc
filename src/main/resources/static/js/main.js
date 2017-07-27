$(document).ready(function () {
    $('#fixed-table-toolbar--buttons').prependTo('.fixed-table-toolbar');
});

function nameSorter(a, b) {
    textFirst = a.slice(a.indexOf('>') + 1);
    textSecond = b.slice(b.indexOf('>') + 1);
    return textFirst.localeCompare(textSecond);
}
