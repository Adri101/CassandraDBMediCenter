
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.swing.JButton;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 *
 * @author Victor
 */
public class mediCenter extends javax.swing.JFrame {
	public static final String SEPARADOR = ";";
	private static String serverIP = "127.0.0.1";
	private static String system_keyspace = "system";
	private Session session;
	private static String my_first_keyspace = "centrosmedicos";

	public void cargarDatos() {

		// TODO Auto-generated method stub

		Cluster cluster = Cluster.builder().addContactPoints(serverIP).build();

		String cqlStatement;

		session = cluster.connect(my_first_keyspace);

		cqlStatement = "DROP KEYSPACE centrosmedicos";

		session.execute(cqlStatement);

		// Creating a Keyspace, similar to a SQL schema
		cqlStatement = "CREATE KEYSPACE centrosmedicos WITH replication ={'class': 'SimpleStrategy', 'replication_factor': '1'} AND durable_writes='true';";

		session.execute(cqlStatement);

		cqlStatement = "USE centrosmedicos";

		session.execute(cqlStatement);

		BufferedReader bufferLectura = null;
		try {
			// Abrir el .csv en buffer de lectura
			bufferLectura = new BufferedReader(new InputStreamReader(new FileInputStream("medico.csv"), "UTF8"));

			// Leer una linea del archivo
			String linea = bufferLectura.readLine();
			String[] cabecera = linea.split(SEPARADOR);
			cqlStatement = "CREATE TABLE centros (" + cabecera[0] + " varchar PRIMARY KEY," + cabecera[1] + " varchar ,"
					+ cabecera[2] + " varchar ," + cabecera[3] + " varchar ," + cabecera[4] + " varchar ," + cabecera[5]
					+ " varchar ," + cabecera[6] + " varchar ," + cabecera[7] + " varchar ," + cabecera[8]
					+ " varchar ," + cabecera[9] + " varchar ," + cabecera[10] + " varchar ," + cabecera[11]
					+ " varchar ," + cabecera[12] + " varchar ," + cabecera[13] + " varchar ," + cabecera[14]
					+ " varchar ," + cabecera[15] + " varchar ," + cabecera[16] + " varchar ," + cabecera[17]
					+ " varchar ," + cabecera[18] + " varchar ," + cabecera[19] + " varchar ," + cabecera[20]
					+ " varchar ," + cabecera[21] + " varchar ," + cabecera[22] + " varchar ," + cabecera[23]
					+ " varchar ," + cabecera[24] + " varchar ," + cabecera[25] + " varchar ," + cabecera[26]
					+ " varchar ," + cabecera[27] + " varchar ," + cabecera[28] + " varchar ," + cabecera[29]
					+ " varchar " + ");";

			session.execute(cqlStatement);

			while (linea != null) {
				linea = bufferLectura.readLine();
				if (linea != null) {
					// Sepapar la linea leída con el separador definido previamente
					String campos[] = linea.split(SEPARADOR);
					String cqlStatementC = "INSERT INTO centrosmedicos.centros (PK, NOMBRE,DESCRIPCIONENTIDAD, HORARIO, EQUIPAMIENTO, TRANSPORTE,DESCRIPCION, ACCESIBILIDAD"
							+ ",CONTENTURL,NOMBREVIA,CLASEVIAL,TIPONUM,NUM,PLANTA ,PUERTA,ESCALERAS,ORIENTACION ,LOCALIDAD,"
							+ "PROVINCIA ,CODIGOPOSTAL ,BARRIO ,DISTRITO ,COORDENADAX ,COORDENADAY ,LATITUD ,LONGITUD ,TELEFONO ,FAX ,EMAIL ,TIPO) "
							+ "VALUES ('" + campos[0] + "','" + campos[1] + "','" + campos[2] + "','" + campos[3]
							+ "','" + campos[4] + "','" + campos[5] + "'" + ",'" + campos[6] + "','" + campos[7] + "','"
							+ campos[8] + "','" + campos[9] + "','" + campos[10] + "','" + campos[11] + "','"
							+ campos[12] + "','" + campos[13] + "'," + "'" + campos[14] + "','" + campos[15] + "','"
							+ campos[16] + "','" + campos[17] + "','" + campos[18] + "','" + campos[19] + "','"
							+ campos[20] + "','" + campos[21] + "'" + ",'" + campos[22] + "','" + campos[23] + "','"
							+ campos[24] + "','" + campos[25] + "','" + campos[26] + "','" + campos[27] + "','"
							+ campos[28] + "','" + campos[29] + "')";

					session.execute(cqlStatementC);
				} // interchangeable, put any of the
					// Volver a leer otra línea del fichero

			}
			session.execute("CREATE INDEX IF NOT EXISTS idx_codigopostal ON centrosmedicos.centros (CODIGOPOSTAL);");
			session.execute("CREATE INDEX IF NOT EXISTS idx_barrio ON centrosmedicos.centros (BARRIO);");
			session.execute("CREATE INDEX IF NOT EXISTS idx_distrito ON centrosmedicos.centros (DISTRITO);");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Cierro el buffer de lectura
			if (bufferLectura != null) {
				try {
					bufferLectura.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Creates new form mediCenter
	 */
	Tabla t = new Tabla();

	public mediCenter() {
		initComponents();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		cargarDatos();
		this.getContentPane().setBackground(new Color(62, 169, 220));
		this.setExtendedState(MAXIMIZED_BOTH);
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		tabla = new javax.swing.JTable();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jLabel19 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 48)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(0, 51, 51));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("MediCenter");
		
		jLabel6.setFont(new java.awt.Font("Calibri Light", 1, 13)); // NOI18N
		jLabel7.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel8.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel9.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel10.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel11.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel12.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel14.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel15.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel16.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel17.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel18.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel19.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel20.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel21.setFont(new java.awt.Font("Calibri Light", 1, 13));
		jLabel13.setFont(new java.awt.Font("Calibri Light", 1, 13));
		
		jLabel2.setText("Filtrar por Código Postal:");
		t.ver_Tabla(tabla, -1, session, jTextField1.getText());
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});
		jLabel3.setText("Filtrar por Barrio:");

		jLabel4.setText("Filtrar por Distrito:");

		jTextField2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField2ActionPerformed(evt);
			}
		});

		jTextField3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField3ActionPerformed(evt);
			}
		});

		jButton1.setText("Filtrar");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Filtrar");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("Filtrar");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tablaMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tabla);
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel5.setText("Detalles");
		jLabel6.setText("Nombre:");

		jLabel7.setText("Descripcion Entidad:");

		jLabel8.setText("Horario:");

		jLabel9.setText("Equipamiento:");

		jLabel10.setText("Transporte:");

		jLabel11.setText("Descripcion:");

		jLabel12.setText("Direccion:");

		jLabel13.setText("Clase via:");

		jLabel14.setText("Num:");

		jLabel15.setText("Localidad:");

		jLabel16.setText("Codigo Postal:");

		jLabel17.setText("Barrio:");

		jLabel18.setText("Distrito:");

		jLabel19.setText("Telefono:");

		jLabel20.setText("Fax:");

		jLabel21.setText("Email:");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addContainerGap(471, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addContainerGap())
        );

		pack();
	}// </editor-fold>

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		t.ver_Tabla(tabla, 0, session, jTextField1.getText());

	}

	private void tablaMouseClicked(java.awt.event.MouseEvent evt) {
		int column = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
		int row = evt.getY() / tabla.getRowHeight();
		int row2 = tabla.getSelectedRow();
		if (row < tabla.getRowCount() && row >= 0 && column < tabla.getColumnCount() && column >= 0) {
			Object value = tabla.getValueAt(row, column);
			if (value instanceof JButton) {
				((JButton) value).doClick();
				JButton boton = (JButton) value;
				if (row2 != -1) {
					String id = (String) tabla.getValueAt(row, 0);
					String consultaDefault = "select * from centros where PK='" + id + "';";
					String cadena = "";
					for (Row fila : session.execute(consultaDefault)) {
						jLabel6.setText("Nombre: " + fila.getString("NOMBRE"));
						jLabel7.setText("Descripcion Entidad: " + fila.getString("DESCRIPCIONENTIDAD"));
						jLabel8.setText("Horario: " + fila.getString("HORARIO"));
						jLabel9.setText("Equipamiento: " + fila.getString("EQUIPAMIENTO"));
						jLabel10.setText("Transporte: " + fila.getString("TRANSPORTE"));
						jLabel11.setText("Descripcion: " + fila.getString("DESCRIPCION"));
						jLabel12.setText("Direccion: " + fila.getString("NOMBREVIA"));
						jLabel13.setText("Clase via: " + fila.getString("CLASEVIAL"));
						jLabel14.setText("Num: " + fila.getString("NUM"));
						jLabel15.setText("Localidad: " + fila.getString("LOCALIDAD"));
						jLabel16.setText("Codigo Postal: " + fila.getString("CODIGOPOSTAL"));
						jLabel17.setText("Barrio: " + fila.getString("BARRIO"));
						jLabel18.setText("Distrito: " + fila.getString("DISTRITO"));
						jLabel19.setText("Telefono: " + fila.getString("TELEFONO"));
						jLabel20.setText("Fax: " + fila.getString("FAX"));
						jLabel21.setText("Email: " + fila.getString("EMAIL"));

					}

				}
			}
		}
	}

	private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		t.ver_Tabla(tabla, 1, session, jTextField2.getText());
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		t.ver_Tabla(tabla, 2, session, jTextField3.getText());
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(mediCenter.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(mediCenter.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(mediCenter.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(mediCenter.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new mediCenter().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTable tabla;
	// End of variables declaration
}
