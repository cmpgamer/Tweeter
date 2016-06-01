<%-- 
    Document   : twit
    Created on : Nov 22, 2015, 3:32:10 PM
    Author     : Kenny
--%> 
<div class="panel panel-info">
    <div class="panel-heading">Twit Info</div>
    <div class="panel-body">
       
        <form class="form-horizontal" action="sendTwit.jsp" method="POST">
            <div class="form-group">
             <div class="row">
                 <div class="col-sm-offset-1 col-sm-10">
                    <textarea class="form-control" name="message" style="width:100%"></textarea>
                 </div>
             </div>
           </div>
           <div class="form-group">
               <div class="col-sm-12">
                    <div class="checkbox text-center">
                         <label>
                              <input type="checkbox" name="isPrivate"> Make Private
                         </label>
                    </div>
               </div>
           </div>
           <hr>
           <div class="form-group">
              <div class="col-sm-offset-1 col-sm-10">
                <button style="width:100%" type="submit" class="btn btn-primary">Send Twit</button>
              </div>
           </div> 
        </form>
    </div>
</div>
