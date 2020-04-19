var a = new Array(3);
a[0] = "行";
a[1] = 123;
a[2] = true;

document.write(a[0] + "<br>")
document.write(a[1] + "<br>")
document.write(a[2] + "<br>")
document.write(a.length+ "<br>")

var student = [
	{id:1, name:"张三", gender:"male"},
	{id:2, name:"小红", gender:"female"},
];
document.write(student[0].id+ "<br>");
document.write(student[1].id+ "<br>");

document.write("=====================");
document.write("<br>");

var b = 5;
var c = "5";
// ==只比较值,内容,不关心其他
document.write(b==c);
document.write("<br>");
// === 会比较类型和值，内容
document.write(b===c);
document.write("<br>");