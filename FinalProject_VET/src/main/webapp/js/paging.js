function pageFirst(){
	location.href="./questBoard.do?page=1"
}
 
function pagePrev(stagePage, countPage, queryString){
	if(!queryString) queryString = '';
	var page = (stagePage-countPage)<0 ? 1:(stagePage-countPage);
	location.href="./questBoard.do?page="+page+queryString;
	
}
 
function page(page, queryString){
	if(!queryString) queryString = '';
	location.href="./questBoard.do?page="+page+queryString;
	
}

function pageNext(stagePage, countPage, queryString){
	if(!queryString) queryString = '';
	location.href="./questBoard.do?page="+(stagePage+countPage)+queryString;
	
}

function pageLast(totalPage){
	location.href="./questBoard.do?page="+totalPage;
	
}