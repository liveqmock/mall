<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.awt.*,java.text.*,java.util.*" %>
<%@ page import="org.jfree.chart.*" %>
<%@ page import="org.jfree.chart.axis.*" %>
<%@ page import="org.jfree.chart.labels.StandardCategoryItemLabelGenerator" %>
<%@ page import="org.jfree.chart.plot.*" %>
<%@ page import="org.jfree.chart.renderer.*" %>
<%@ page import="org.jfree.chart.servlet.ServletUtilities" %>
<%@ page import="org.jfree.data.category.*" %>
<%@ page import="org.jfree.ui.TextAnchor" %>
<%@ page import="org.jfree.data.general.*"%>
<%@ page import="org.jfree.chart.renderer.category.*" %>
<%
StandardChartTheme theme = new StandardChartTheme("unicode") {
	public void apply(JFreeChart chart) {  
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
		RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
		super.apply(chart);  
		}  
		};  
		theme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 20));  
		theme.setLargeFont(new Font("宋体", Font.PLAIN, 14));  
		theme.setRegularFont(new Font("宋体", Font.PLAIN, 12));  
		theme.setSmallFont(new Font("宋体", Font.PLAIN, 10));  
		ChartFactory.setChartTheme(theme);  
	//The data for the bar chart
	double[] data = { 85, 156, 179.5, 211, 123 };
	//The labels for the bar chart
	String[] labels = { "Mon", "Tue", "Wed", "Thu", "Fri" };

	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	for (int i = 0; i < data.length; i++) {
		dataset.addValue(data[i], "", labels[i]);
	}

	JFreeChart chart = ChartFactory.createBarChart3D(
			"Weekly Server Load", "Work Week 25", "MBytes", dataset,
			PlotOrientation.VERTICAL, false, false, false);
	chart.setBackgroundPaint(new Color(0xE1E1E1));

	CategoryPlot plot = chart.getCategoryPlot();

	// 设置Y轴显示整数
	NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

	CategoryAxis domainAxis = plot.getDomainAxis();
	//设置距离图片左端距离
	domainAxis.setLowerMargin(0.05);

	BarRenderer3D renderer = new BarRenderer3D();
	//设置柱的颜色
	renderer.setSeriesPaint(0, new Color(0xff00));
	plot.setRenderer(renderer);

	String filename = ServletUtilities.saveChartAsPNG(chart, 300, 280,
			null, session);
	String graphURL = request.getContextPath()
			+ "/DisplayChart?filename=" + filename;
%>
<html>
<body topmargin="5" leftmargin="5" rightmargin="0">
<div style="font-size:18pt; font-family:verdana; font-weight:bold">
3D Bar Chart
</div>
<br>
<img src="<%=graphURL%>" border=0>
</body>
</html>
<%
	double[][] datas = new double[][] { { 1310 }, { 720 }, { 1130 },
			{ 440 } };
	String[] rowKeys = { "猪肉", "牛肉", "鸡肉", "鱼肉" };
	String[] columnKeys = { "" };
	CategoryDataset datasets = DatasetUtilities.createCategoryDataset(
			rowKeys, columnKeys, datas);
	JFreeChart charts = ChartFactory
			.createBarChart3D("广州肉类销量统计图", "肉类", "销量", datasets,
					PlotOrientation.VERTICAL, true, false, false);
	
	String filenames = ServletUtilities.saveChartAsPNG(charts, 500, 300,
			null, session);
	String graphURLs = request.getContextPath()
			+ "/DisplayChart?filename=" + filenames;
%>
<img src="<%=graphURLs%>" width=500 height=300 border=0
	usemap="#<%= filename %>">

