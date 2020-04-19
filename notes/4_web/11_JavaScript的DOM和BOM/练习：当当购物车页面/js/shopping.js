
function close_plan(){
	var result = confirm("确定关闭吗?");
	if(result==true){
		window.close();
	}
}
function del(obj){
	var result = confirm("确定删除吗?");
	if(result==true){
		// var o = obj.parentNode.parentNode;
		// o.remove();
		alert("删除成功!");
	}
}
function collection(){
	var result = confirm("确定移入收藏夹吗?");
	if(result==true){
		// var o = obj.parentNode.parentNode;
		// o.remove();
		alert("收藏成功!");
	}
}
function accounts(){
	var flag=confirm("您本次购买的商品信息如下：\n\n商品名称：白岩松：白说、岛上书店；\n商品数量：2件；\n商品总计：46.00；\n运费：0元；\n\n请确认以上信息是否有误！！！");
	if(flag==true){
		alert("结算成功!请您立即付款");
	}
}
function minus(num){
	var sign = document.getElementsByName("minus")[num].value;
	var count = parseInt(document.getElementsByName("amount")[num].value)-1;
	if(count<1){
		alert("不能再减了!最少一件");
	}else{
		document.getElementsByName("amount")[num].value = count;
		var totals= parseFloat(document.getElementsByName("price")[num].value)*count;
		var countPrice =  document.getElementById("price"+num);
		countPrice.innerText ="¥" +totals;
	}
	total();
}
function plus(num){
	var count = parseInt(document.getElementsByName("amount")[num].value)+1;
	document.getElementsByName("amount")[num].value = count;
	
	var totals= parseFloat(document.getElementsByName("price")[num].value)*count;
	var countPrice =  document.getElementById("price"+num);
	countPrice.innerText ="¥" +totals;
	total();
}
function total(){
	var totalprice = document.getElementById("totalPrice");
	var prices = document.getElementsByName("price");
	var amounts =document.getElementsByName("amount");
	var first = parseFloat(prices[0].value) * parseInt(amounts[0].value);
	var second = parseFloat(prices[1].value) * parseInt(amounts[1].value);
	totalprice.innerText = first+second;
}
total();