/** 첨부 파일 관련 처리 함수  **/

$(document).on('change', '#attach-file', function() {
	var attached = this.files[0];
	if ( attached ) {	// 첨부된 파일이 있을 경우 파일명을 나타내고 삭제 이미지도 표시함.
		$('#file-name').text(attached.name);
		$('#delete-file').css('display', 'inline');
		
		if($('#preview').length > 0 ) {
			if(isImage(attached.name)){	// 이미지 파일이면 미리보기 처리를! 하고!
				$('#preview').html('<img src="" id="preview-img"  />  ');
				
				var reader = new FileReader();
				reader.onload = function(e) {
					$('#preview-img').attr('src' , e.target.result	);
				}
				reader.readAsDataURL( attached );
			}else
			$('#preview').html('');	//이미지 파일이 아니면 미리보기 처리하지 않음.
		}
		
		
	} else {
		$('#file-name').text('');
		$('#delete-file').css('display', 'none');
	}
}).on('click', '#delete-file', function() {	//첨부 파일 삭제 시
	if($('#preview').length > 0 ) $('#preview').html('');
	$('#file-name').text('');
	$('#delete-file').css('display', 'none');
	
	$('#attach-file').val('');	// 파일 태그의 첨부된 파일 정보 없애기
	
})

/* 이미지 파일 여부 확인 함수 */
function isImage(filename){
	// abc.txt, abc.png, abc.pptx ... 확장자를 통해 파일의 종류를 판단 
	var ext = filename.substring( filename.lastIndexOf('.')+1 ).toLowerCase();		//파지막에서부터 ' . ' 을 찾는다 . 뒤에 있던 확장자를 찾았다
	
	// 확장자 jpg, png, gif, jpeg, bmp, tiff, pcx 등
	var imgs = ['png','jpg','gif','jpeg','bmp','tiff','pcx'];
	
	if( imgs.indexOf( ext ) > -1 ) return true;
	else return false;
}