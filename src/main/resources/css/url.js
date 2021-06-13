const params = new URLSearchParams(window.location.search);

const name = params.get("search");
const name = params.get("date1");
const name = params.get("date2");


console.log(search); 
console.log(date1);
console.log(date2);

URL.resolve