/*
function f(){
	return {};
}
const replyService = f();
*/
const reviewService = (function(){
	function insert(review,callback){
		$.ajax({
			type:"POST",
			url:"/review/regist",
			data:JSON.stringify(review),
			contentType:"application/json;charset=utf-8",
			success:function(result,status,xhr){
				callback(result);
			}
		});
	}
	
	function selectAll(data,callback){
		let boardNum = data.boardNum;
		let pagenum = data.pagenum;
		
		$.getJSON(
			"/review/pages/"+boardNum+"/"+pagenum,
			function(data){
				//data : {replyCnt:댓글개수, list:[....]}
				callback(data.reviewCnt, data.list);
			}
		)
	}
	
	function drop(reviewNum,callback,error){
		$.ajax({
			type:"DELETE",
			url:"/review/"+reviewNum,
			success:function(result,status,xhr){
				callback(result);
			},
			error:function(xhr,status,err){
				error(err);	
			}
		})
	}
	
	function update(review,callback,error){
		$.ajax({
			type:"PUT",
			url:"/review/"+review.reviewNum,
			data:JSON.stringify(review),
			contentType:"application/json;charset=utf-8",
			success:function(result){
				if(callback){
					callback(result);
				}
			},
			error:function(err){
				if(error){
					error(err);
				}
			}
		})
	}
	
	function fmtTime(review){
		const regdate = review.regdate;
		const updatedate = review.updatedate;
		
		const now = new Date();
		
		const check = regdate == updatedate;
		
		const dateObj = new Date(check ? regdate : updatedate);
		//date객체.getTime() : date객체가 가지고 있는 시간 정보를 밀리초 단위로 추출
		let gap = now.getTime() - dateObj.getTime();
		
		let str = "";
		if(gap < 1000*60*60*24){
			let hh = dateObj.getHours();
			let mi = dateObj.getMinutes();
			let ss = dateObj.getSeconds();
			str = (hh>9?'':'0')+hh+":"+(mi>9?'':'0')+mi+":"+(ss>9?'':'0')+ss;
		}
		else{
			let yy = dateObj.getFullYear();
			let mm = dateObj.getMonth()+1;
			let dd = dateObj.getDate();
			
			str = (yy>9?'':'0')+yy+"/"+(mm>9?'':'0')+mm+":"+(dd>9?'':'0')+dd;
		}
		return (check?'':'(수정됨) ')+str;
	}
	
	
	return {add:insert, getList:selectAll, remove:drop, modify:update, displayTime:fmtTime}
})();









