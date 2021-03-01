/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 *
 * @author Victor
 */
public class Tabla {
	public void ver_Tabla(JTable tabla, int opcion, Session session, String filtrado) {
		tabla.setDefaultRenderer(Object.class, new render());
		JButton btn1 = new JButton("Detalles");
		DefaultTableModel d1 = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		d1.addColumn("PK");
		d1.addColumn("Nombre");
		d1.addColumn("URL");
		d1.addColumn("Detalles");
		switch (opcion) {
		case 0:
			if (filtrado != "") {
				
				String consultaPostal = "select pk, nombre, contenturl from centros where CODIGOPOSTAL='\"" + filtrado
						+ "\"';";
				for (Row row : session.execute(consultaPostal)) {
					String[] r = new String[4];
					
					r[0] = row.getString("PK");
					r[1] = row.getString("NOMBRE");
					r[2] = row.getString("CONTENTURL");
					d1.addRow(new Object[] { r[0], r[1], r[2], btn1 });
				}
			}
			break;
		case 1:
			if (filtrado != "") {
				
				String consultaBarrio = "select pk, nombre, contenturl from centros where BARRIO='\"" + filtrado
						+ "\"';";
				for (Row row : session.execute(consultaBarrio)) {
					String[] r = new String[4];
					
					r[0] = row.getString("PK");
					r[1] = row.getString("NOMBRE");
					r[2] = row.getString("CONTENTURL");
					d1.addRow(new Object[] { r[0], r[1], r[2], btn1 });
				}
			}
			break;
		case 2:
			if (filtrado != "") {
				
				String consultaDistrito = "select pk, nombre, contenturl from centros where DISTRITO='\"" + filtrado
						+ "\"';";
				for (Row row : session.execute(consultaDistrito)) {
					String[] r = new String[4];
					System.out.println("row: " + row.toString());
					r[0] = row.getString("PK");
					r[1] = row.getString("NOMBRE");
					r[2] = row.getString("CONTENTURL");
					d1.addRow(new Object[] { r[0], r[1], r[2], btn1 });
				}
			}
			break;
		default:
			System.out.println(filtrado);
			String consultaDefault = "select pk, nombre, contenturl from centros;";
			for (Row row : session.execute(consultaDefault)) {
				String[] r = new String[4];
				r[0] = row.getString("PK");
				r[1] = row.getString("NOMBRE");
				r[2] = row.getString("CONTENTURL");
				d1.addRow(new Object[] { r[0], r[1], r[2], btn1 });
			}
			break;
		}

		tabla.setModel(d1);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabla.getColumnModel().getColumn(0).setMinWidth(0);
		tabla.getColumnModel().getColumn(0).setMaxWidth(0);
		tabla.setRowHeight(30);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(500);
		tabla.getColumnModel().getColumn(1).setMinWidth(500);
		tabla.getColumnModel().getColumn(1).setMaxWidth(500);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(3).setMinWidth(100);
		tabla.getColumnModel().getColumn(3).setMaxWidth(100);
	}
}
