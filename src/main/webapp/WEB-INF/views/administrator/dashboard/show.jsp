<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.entities.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.entities.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPublicTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfPublicTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPrivateTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfPrivateTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfFinishedTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfNonFinishedTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfNonFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${avgExecutionPeriodTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${dvtExecutionPeriodTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${minExecutionPeriodTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${maxExecutionPeriodTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${avgWorkloadTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${dvtWorkloadTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${minWorkloadTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${maxWorkloadTasks}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPublicWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfPublicWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPrivateWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfPrivateWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfFinishedWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfFinishedWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfNonFinishedWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfNonFinishedWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfWorkPlanPeriods"/>
		</th>
		<td>
			<acme:print value="${avgExecutionPeriodWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfWorkPlanPeriods"/>
		</th>
		<td>
			<acme:print value="${dvtExecutionPeriodWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfWorkPlanPeriods"/>
		</th>
		<td>
			<acme:print value="${minExecutionPeriodWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfWorkPlanPeriods"/>
		</th>
		<td>
			<acme:print value="${maxExecutionPeriodWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${avgWorkloadWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${dvtWorkloadWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${minWorkloadWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${maxWorkloadWorkplans}"/>
		</td>
	</tr>
	</table>
	<table class="table table-sm">
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.entities.label.numberOfWorkPlans"/>
			</th>
			<td>
				<acme:print value="${totalNumberOfWorkplans}"/>
			</td>
		</tr>	
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.entities.label.numberOfPublishedWorkPlan"/>
			</th>
			<td>
				<acme:print value="${totalNumberOfPublishedWorkplans}"/>
			</td>
		</tr>	
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.entities.label.numberOfNonPublishedWorkPlans"/>
			</th>
			<td>
				<acme:print value="${totalNumberOfNonPublishedWorkPlans}"/>
			</td>
		</tr>										
	</table>
<h2>
	<acme:message code="administrator.dashboard.entities.title.application-statuses"/>
</h2>

<div>
	<canvas id="myCanvas"></canvas>
</div>

<script> 
<script>
var myCanvas = document.getElementById("myCanvas");
myCanvas.width = 300;
myCanvas.height = 300;
 
var ctx = myCanvas.getContext("2d");
 
function drawLine(ctx, startX, startY, endX, endY){
    ctx.beginPath();
    ctx.moveTo(startX,startY);
    ctx.lineTo(endX,endY);
    ctx.stroke();
 
}

function drawArc(ctx, centerX, centerY, radius, startAngle, endAngle){
    ctx.beginPath();
    ctx.arc(centerX, centerY, radius, startAngle, endAngle);
    ctx.stroke();
}
 
function drawPieSlice(ctx,centerX, centerY, radius, startAngle, endAngle, color ){
    ctx.fillStyle = color;
    ctx.beginPath();
    ctx.moveTo(centerX,centerY);
    ctx.arc(centerX, centerY, radius, startAngle, endAngle);
    ctx.closePath();
    ctx.fill();
}

var Piechart = function (options) {
      this.options = options;
      this.canvas = options.canvas;
      this.ctx = this.canvas.getContext("2d");
      this.colors = options.colors;
      
      this.draw = function () {
        var total_value = 0;
        var color_index = 0;

        for (var categ in this.options.data) {
          var val = this.options.data[categ];
          total_value += val;
        }

        var start_angle = 0;

        for (categ in this.options.data) {
          val = this.options.data[categ];
          var slice_angle = (2 * Math.PI * val) / total_value;
          drawPieSlice(
            this.ctx,
            this.canvas.width / 2,
            this.canvas.height / 2,
            Math.min(this.canvas.width / 2, this.canvas.height / 2),
            start_angle,
            start_angle + slice_angle,
            this.colors[color_index % this.colors.length]
          );

          start_angle += slice_angle;
          color_index++;
        }
      };
    };
    
    var myVinyls = {
             
            "Classical music": 10,
         
            "Alternative rock": 14,
         
            "Pop": 2,
         
            "Jazz": 12
         
        };

    var myPiechart = new Piechart(
        {
            canvas: myCanvas,
            data:myVinyls,
            
            colors:["#fde23e","#f16e23", "#57d9ff","#937e88"]
        }
    );
    myPiechart.draw();
    </script>