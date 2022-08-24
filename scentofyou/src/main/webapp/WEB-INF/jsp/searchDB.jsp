<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.scentofyou.scentofyou.domain.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table id="boardtable">
		<tbody>
		</tbody>
		<tr>
			<%
			String name = request.getParameter("perfumeName");
			String category = request.getParameter("categoryType");
			String gender = request.getParameter("genderType");

			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			String jdbc_driver = "com.mysql.cj.jdbc.Driver";
			String jdbc_url = "jdbc:mysql://mydatabase.cl88hjaqluom.ap-northeast-2.rds.amazonaws.com:3306/mydb";

			String id = "root";
			String pw = "dalcteam2";

			try {
				Class.forName(jdbc_driver);
				conn = DriverManager.getConnection(jdbc_url, id, pw);
				stmt = conn.createStatement();

				String searchValue = name;
				searchValue = "'%" + searchValue + "%'";

				String sql = "select perfume_name, perfume_img from mydb.Perfume where perfume_name LIKE " + searchValue
				+ " and perfume_category='" + category + "' and perfume_gender='" + gender + "' limit 30";
				rs = stmt.executeQuery(sql);

				List<Perfume> list = new ArrayList<Perfume>();
				while (rs.next()) {
					Perfume p = new Perfume();

					p.setName(rs.getString(1));
					p.setPerfumeImg(rs.getString(2));
					list.add(p);
				}
				if (list.isEmpty())
					out.println("해당하는 향수를 찾지 못했습니다.");
				else {
					for (Perfume p : list) {
				out.println("<td>" + p.getName() + "</td>");
				out.println("<td><img style='width:500px; height:700px;'src='" + p.getPerfumeImg() + "' /></td>");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			%>
		</tr>
	</table>
	<button onclick="history.back()">Back</button>
</body>
</html>