;(function($){$.fn.SliderImgPuzzle=function(setting){var defaults={callback:false}
var setting=$.extend(defaults,setting);const l=42,r=10,w=270,h=155,PI=Math.PI
const L=l+r*2
function getRandomNumberByRange(start,end){return Math.round(Math.random()*(end-start)+start)}
function createCanvas(width,height){const canvas=createElement('canvas')
canvas.width=width
canvas.height=height
return canvas}
function createImg(onload){const img=createElement('img')
img.crossOrigin="Anonymous"
img.onload=onload
img.onerror=()=>{img.src=getRandomImg()}
img.src=getRandomImg()
return img}
function createElement(tagName){return document.createElement(tagName)}
function addClass(tag,className){tag.classList.add(className)}
function removeClass(tag,className){tag.classList.remove(className)}
// function getRandomImg(){return 'https://picsum.photos/300/150/?image='+getRandomNumberByRange(0,1084)}
function getRandomImg(){return '/static/design/slide/randomImg/'+getRandomNumberByRange(0,5)+".jpeg"}
function draw(ctx,operation,x,y,type)
{if(type==1)
draw1(ctx,operation,x,y);else if(type==2)
draw2(ctx,operation,x,y);else if(type==3)
draw3(ctx,operation,x,y);else if(type==4)
draw4(ctx,operation,x,y);else if(type==5)
draw5(ctx,operation,x,y);else if(type==6)
draw6(ctx,operation,x,y);}
function draw6(ctx,operation,x,y){ctx.beginPath()
ctx.moveTo(x,y)
ctx.lineTo(x+l,y)
ctx.lineTo(x+l,y+l/2)
ctx.arc(x+l+r-4,y+l/2,r,0,2*PI)
ctx.lineTo(x+l,y+l/2)
ctx.lineTo(x+l,y+l)
ctx.lineTo(x+l/2,y+l)
ctx.arc(x+l/2,y+l+4,r,0,2*PI)
ctx.lineTo(x+l/2,y+l)
ctx.lineTo(x,y+l)
ctx.lineTo(x,y)
ctx.fillStyle='#fff'
ctx[operation]()
ctx.beginPath()
ctx.arc(x+l/2,y,r,2*PI,1*PI)
ctx.globalCompositeOperation="xor"
ctx.fill()}
function draw5(ctx,operation,x,y){ctx.beginPath()
ctx.moveTo(x,y)
ctx.lineTo(x+l/2,y)
ctx.arc(x+l/2,y-r+4,r,0,2*PI)
ctx.lineTo(x+l/2,y)
ctx.lineTo(x+l,y)
ctx.lineTo(x+l,y+l/2)
ctx.arc(x+l+r-4,y+l/2,r,0,2*PI)
ctx.lineTo(x+l,y+l/2)
ctx.lineTo(x+l,y+l)
ctx.lineTo(x,y+l)
ctx.lineTo(x,y)
ctx.fillStyle='#fff'
ctx[operation]()
ctx.beginPath()
ctx.arc(x+l/2,y+l,r,1*PI,2*PI)
ctx.globalCompositeOperation="xor"
ctx.fill()}
function draw4(ctx,operation,x,y){ctx.beginPath()
ctx.moveTo(x,y)
ctx.lineTo(x+l/2,y)
ctx.arc(x+l/2,y-r+4,r,0,2*PI)
ctx.lineTo(x+l/2,y)
ctx.lineTo(x+l,y)
ctx.lineTo(x+l,y+l/2)
ctx.arc(x+l+r-4,y+l/2,r,0,2*PI)
ctx.lineTo(x+l,y+l/2)
ctx.lineTo(x+l,y+l)
ctx.lineTo(x+l/2,y+l)
ctx.arc(x+l/2,y+l+4,r,0,2*PI)
ctx.lineTo(x+l/2,y+l)
ctx.lineTo(x,y+l)
ctx.lineTo(x,y)
ctx.fillStyle='#fff'
ctx[operation]()
ctx.beginPath()
ctx.arc(x,y+l/2,r,1.5*PI,0.5*PI)
ctx.globalCompositeOperation="xor"
ctx.fill()}
function draw3(ctx,operation,x,y){ctx.beginPath()
ctx.moveTo(x,y)
ctx.lineTo(x+l/2,y)
ctx.lineTo(x+l,y)
ctx.lineTo(x+l,y+l/2)
ctx.arc(x+l+r-4,y+l/2,r,0,2*PI)
ctx.lineTo(x+l,y+l/2)
ctx.lineTo(x+l,y+l)
ctx.lineTo(x+l/2,y+l)
ctx.arc(x+l/2,y+l+4,r,0,2*PI)
ctx.lineTo(x+l/2,y+l)
ctx.lineTo(x,y+l)
ctx.lineTo(x,y)
ctx.fillStyle='#fff'
ctx[operation]()
ctx.beginPath()
ctx.arc(x,y+l/2,r,1.5*PI,0.5*PI)
ctx.globalCompositeOperation="xor"
ctx.fill()}
function draw2(ctx,operation,x,y){ctx.beginPath()
ctx.moveTo(x,y)
ctx.lineTo(x+l/2,y)
ctx.lineTo(x+l,y)
ctx.lineTo(x+l,y+l/2)
ctx.arc(x+l+r-4,y+l/2,r,0,2*PI)
ctx.lineTo(x+l,y+l/2)
ctx.lineTo(x+l,y+l)
ctx.lineTo(x,y+l)
ctx.lineTo(x,y)
ctx.fillStyle='#fff'
ctx[operation]()
ctx.beginPath()
ctx.arc(x,y+l/2,r,1.5*PI,0.5*PI)
ctx.globalCompositeOperation="xor"
ctx.fill()}
function draw1(ctx,operation,x,y){ctx.beginPath()
ctx.moveTo(x,y)
ctx.lineTo(x+l/2,y)
ctx.arc(x+l/2,y-r+4,r,0,2*PI)
ctx.lineTo(x+l/2,y)
ctx.lineTo(x+l,y)
ctx.lineTo(x+l,y+l/2)
ctx.arc(x+l+r-4,y+l/2,r,0,2*PI)
ctx.lineTo(x+l,y+l/2)
ctx.lineTo(x+l,y+l)
ctx.lineTo(x,y+l)
ctx.lineTo(x,y)
ctx.fillStyle='#fff'
ctx[operation]()
ctx.beginPath()
ctx.arc(x,y+l/2,r,1.5*PI,0.5*PI)
ctx.globalCompositeOperation="xor"
ctx.fill()}
function sum(x,y){return x+y}
function square(x){return x*x}
class sliderImgPuzzle{constructor({el,onSuccess,onFail,onRefresh,titleText}){this.el=el
this.titleText=titleText
this.onSuccess=onSuccess
this.onFail=onFail
this.onRefresh=onRefresh}
init(){this.initDOM()
this.initImg()
this.draw()
this.bindEvents()
return this;}
initDOM(){const canvas=createCanvas(w,h)
const block=canvas.cloneNode(true)
const canvasContainer=createElement('div')
const sliderImgPuzzleContainer=createElement('div')
const refreshIcon=createElement('div')
const sliderImgPuzzleMask=createElement('div')
const sliderImgPuzzle=createElement('div')
const sliderImgPuzzleIcon=createElement('span')
const text=createElement('span')
canvasContainer.className='canvasContainer'
block.className='block'
sliderImgPuzzleContainer.className='sliderImgPuzzleContainer'
refreshIcon.className='refreshIcon'
sliderImgPuzzleMask.className='sliderImgPuzzleMask'
sliderImgPuzzle.className='sliderImgPuzzle'
sliderImgPuzzleIcon.className='sliderImgPuzzleIcon'
//text.innerHTML='向右滑动滑块提交表单'
text.innerHTML=this.titleText
text.className='sliderImgPuzzleText'
const el=this.el
canvasContainer.appendChild(canvas)
canvasContainer.appendChild(refreshIcon)
canvasContainer.appendChild(block)
el.appendChild(canvasContainer)
sliderImgPuzzle.appendChild(sliderImgPuzzleIcon)
sliderImgPuzzleMask.appendChild(sliderImgPuzzle)
sliderImgPuzzleContainer.appendChild(sliderImgPuzzleMask)
sliderImgPuzzleContainer.appendChild(text)
el.appendChild(sliderImgPuzzleContainer)
Object.assign(this,{canvas,block,canvasContainer,sliderImgPuzzleContainer,refreshIcon,sliderImgPuzzle,sliderImgPuzzleMask,sliderImgPuzzleIcon,text,canvasCtx:canvas.getContext('2d'),blockCtx:block.getContext('2d')})}
initImg(){const img=createImg(()=>{this.canvasCtx.drawImage(img,0,0,w,h)
this.blockCtx.drawImage(img,0,0,w,h)
const y=this.y-r*2+2
const ImageData=this.blockCtx.getImageData(this.x,y,L+r+4,L+r+4)
this.block.width=L
this.blockCtx.putImageData(ImageData,0,y)})
this.img=img}
draw(){this.x=getRandomNumberByRange(L+10,w-(L+10))
this.y=getRandomNumberByRange(10+r*2,h-(L+10))
var num=Math.floor(Math.random()*6+1);draw(this.canvasCtx,'fill',this.x,this.y,num)
draw(this.blockCtx,'clip',this.x,this.y,num)}
clean(){this.canvasCtx.clearRect(0,0,w,h)
this.blockCtx.clearRect(0,0,w,h)
this.block.width=w}
bindEvents(){this.el.onselectstart=()=>false
this.refreshIcon.onclick=()=>{this.reset()
typeof this.onRefresh==='function'&&this.onRefresh()}
let originX,originY,trail=[],isMouseDown=false
this.sliderImgPuzzle.ctrl=this;this.sliderImgPuzzle.addEventListener('mousedown',function(e){if(!this.parentElement.parentElement.classList.contains("sliderImgPuzzleContainer_success"))
{originX=e.x,originY=e.y
isMouseDown=true}
else
{isMouseDown=false}
if(!this.ctrl.isEnable)isMouseDown=false;})
this.sliderImgPuzzle.addEventListener('mouseover',function(e){if(this.ctrl.isEnable)
this.parentElement.parentElement.parentElement.querySelector(".canvasContainer").style.display="block";})
this.canvasContainer.parentElement.addEventListener('mouseleave',function(e){this.querySelector(".canvasContainer").style.display="none";})
document.addEventListener('mousemove',(e)=>{if(!isMouseDown)return false
const moveX=e.x-originX
const moveY=e.y-originY
if(moveX<0||moveX+38>=w)return false
this.sliderImgPuzzle.style.left=moveX+'px'
var blockLeft=(w-40-20)/(w-40)*moveX
this.block.style.left=blockLeft+'px'
addClass(this.sliderImgPuzzleContainer,'sliderImgPuzzleContainer_active')
this.sliderImgPuzzleMask.style.width=moveX+'px'
trail.push(moveY)})
document.addEventListener('mouseup',(e)=>{if(!isMouseDown)return false
isMouseDown=false
if(e.x==originX)return false
removeClass(this.sliderImgPuzzleContainer,'sliderImgPuzzleContainer_active')
this.trail=trail
const{spliced,TuringTest}=this.verify()
if(spliced){if(TuringTest){addClass(this.sliderImgPuzzleContainer,'sliderImgPuzzleContainer_success')
typeof this.onSuccess==='function'&&this.onSuccess()}else{addClass(this.sliderImgPuzzleContainer,'sliderImgPuzzleContainer_fail')
this.text.innerHTML='再试一次'
this.reset()}}else{addClass(this.sliderImgPuzzleContainer,'sliderImgPuzzleContainer_fail')
typeof this.onFail==='function'&&this.onFail()
setTimeout(()=>{this.reset()},1000)}})}
verify(){const arr=this.trail
const average=arr.reduce(sum)/arr.length
const deviations=arr.map(x=>x-average)
const stddev=Math.sqrt(deviations.map(square).reduce(sum)/arr.length)
const left=parseInt(this.block.style.left)
return{spliced:Math.abs(left-this.x)<10,TuringTest:average!==stddev,}}
reset(){this.sliderImgPuzzleContainer.className='sliderImgPuzzleContainer'
this.sliderImgPuzzle.style.left=0
this.block.style.left=0
this.sliderImgPuzzleMask.style.width=0
this.clean()
this.img.src=getRandomImg()
this.draw()}
enablePuzzle(enable){if(enable)
{this.isEnable=true;}
else this.isEnable=false;}}
this.each(function(){var $SliderImgPuzzle=$(this);setting.el=$SliderImgPuzzle[0];var sip=new sliderImgPuzzle(setting).init();$SliderImgPuzzle.data("LsSliderImgPuzzle",sip);});}})(jQuery);