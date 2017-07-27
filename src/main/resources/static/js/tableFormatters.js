function nameSorter(a, b) {
    textFirst = a.slice(a.indexOf('>') + 1);
    textSecond = b.slice(b.indexOf('>') + 1);
    return textFirst.localeCompare(textSecond);
};

function ordersIdLinkFormatter(value, row, index) {
    return "<a href='/admin/orders/" + value + "'>" + value + "</a>";
};

function clientsIdLinkFormatter(value, row, index) {
    return "<a href='/admin/clients/" + value + "'>" + value + "</a>";
};

function contractsIdLinkFormatter(value, row, index) {
    return "<a href='/admin/contracts/" + value + "'>" + value + "</a>";
};
