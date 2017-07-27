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

function employeesIdLinkFormatter(value, row, index) {
    return "<a href='/admin/employees/" + value + "'>" + value + "</a>";
};

function invoicesIdLinkFormatter(value, row, index) {
    return "<a href='/admin/invoices/" + value + "'>" + value + "</a>";
};

function servicesIdLinkFormatter(value, row, index) {
    return "<a href='/admin/services/" + value + "'>" + value + "</a>";
};
