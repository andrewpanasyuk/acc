function nameSorter(a, b) {
    textFirst = a.slice(a.indexOf('>') + 1);
    textSecond = b.slice(b.indexOf('>') + 1);
    return textFirst.localeCompare(textSecond);
};

function salaryIdLinkFormatter(value, row, index) {
    return "<a href='/admin/salary/" + value + "'>" + value + "</a>";
};

function dealsIdLinkFormatter(value, row, index) {
    return "<a href='/admin/deals/" + value + "'>" + value + "</a>";
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

function consultanciesIdLinkFormatter(value, row, index) {
    return "<a href='/admin/consultancies/" + value + "'>" + value + "</a>";
};
