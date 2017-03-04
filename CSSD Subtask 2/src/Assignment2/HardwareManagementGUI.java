/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

/**
 *
 * @author Andy
 */
public class HardwareManagementGUI extends javax.swing.JFrame 
{
    User user = null;
    FarmList farms = new FarmList();
    FieldList fields = new FieldList();
    PlotList plots = new PlotList();
    SensorList sensors = new SensorList();
    Farm selectedFarm = null;
    Field selectedField = null;
    Plot selectedPlot = null;
    Sensor selectedSensor = null;
    
    /**
     * Creates new form HardwareManagementGUI
     * @param user
     */
    public HardwareManagementGUI(User user) 
    {
        initComponents();
        this.user = user;
        farms = user.getFarms();
        lblUsername.setText(user.getUsername());
        getFarmList();
    }
    
    public final void getFarmList()
    {
        farmList.setListData(farms.toArray());
    }
    
    public void setSelectedFarm()
    {
        int index = farmList.getSelectedIndex();
        for (int i = 0; i < farms.size(); i++)
        {
            selectedFarm = farms.get(i);
            if (selectedFarm.getName().equals(farmList.getModel().getElementAt(index).toString()))
            {
                showFarmFields();
                break;
            }
        }
    }
    
    public void setSelectedField()
    {
        int index = fieldList.getSelectedIndex();
        for (int i = 0; i < fields.size(); i++)
        {
            selectedField = fields.get(i);
            if (selectedField.getName().equals(fieldList.getModel().getElementAt(index).toString()))
            {
                showPlots();
                break;
            }
        }
    }
    
    public void setSelectedPlot()
    {
        int index = plotList.getSelectedIndex();
        for (int i = 0; i < plots.size(); i++)
        {
            selectedPlot = plots.get(i);
            if (selectedPlot.getName().equals(plotList.getModel().getElementAt(index).toString()))
            {
                break;
            }
        }
    }
    
    public void setSelectedSensor()
    {
        int index = sensorList.getSelectedIndex();
        
        for (int i = 0; i < sensors.size(); i++)
        {
            selectedSensor = sensors.get(i);
            if (sensorList.getModel().getElementAt(index).equals(selectedSensor))
            {
                break;
            }
        }
    }
    
    public void showFarmFields()
    {
        fields = selectedFarm.getFields();
        fieldList.setListData(fields.toArray());
    }
    
    public void showPlots()
    {
        plots = selectedField.getPlots();
        plotList.setListData(plots.toArray());
    }
    
    //Should these functions be in a different class and pass the variables?
    public void addFieldStation()
    {
        String phone = txtFieldStationPhone.getText();
        String code = txtFieldStationCode.getText();
        String[] coords = txtFieldStationLocation.getText().split(",");
        GPSCoord gps = new GPSCoord(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
        FieldStation fieldStation = new FieldStation(gps, phone, code);
        selectedField.setFieldStation(fieldStation);
    }
    
    public void addSensor()
    {
        long frequency = (int) txtSensorFrequency.getModel().getValue();
        boolean enabled = chkSensorEnabled.isSelected();
        String sensorTypeTxt = cmbSensorType.getSelectedItem().toString();
        SensorType sensorType;
        switch (sensorTypeTxt) {
            case "Air Temperature":  sensorType = SensorType.AIR_TEMPERATURE;
                     break;
            case "Pressure":  sensorType = SensorType.PRESSURE;
                     break;
            case "Soil Moisture":  sensorType = SensorType.SOIL_MOISTURE;
                     break;
            case "Acidity":  sensorType = SensorType.ACIDITY;
                     break;
            case "Light Sensor":  sensorType = SensorType.LIGHT_SENSOR;
                     break;
            case "Soil Temperature":  sensorType = SensorType.SOIL_TEMPERATURE;
                     break;
            default: sensorType = SensorType.AIR_TEMPERATURE;
                     break;
        }
        String[] coords = txtSensorLocation.getText().split(",");
        GPSCoord gps = new GPSCoord(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
        Sensor sensor = new Sensor(gps, enabled, frequency, sensorType);
        selectedPlot.getSensors().add(sensor);
    }
    
    public void editSensor()
    {
        long frequency = (int) txtEditSensorFrequency.getModel().getValue();
        boolean enabled = chkEditSensorEnabled.isSelected();
        String[] coords = txtEditSensorLocation.getText().split(",");
        GPSCoord gps = new GPSCoord(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
        selectedSensor.updateSensorConfig(enabled, frequency, gps);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fieldStationDialog = new javax.swing.JDialog();
        lblFieldStation = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtFieldStationLocation = new javax.swing.JTextField();
        txtFieldStationPhone = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtFieldStationCode = new javax.swing.JTextField();
        btnFieldStationAdd = new javax.swing.JButton();
        btnFieldStationCancel = new javax.swing.JButton();
        sensorManageDialog = new javax.swing.JDialog();
        btnClose = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        sensorList = new javax.swing.JList();
        btnAddSensor = new javax.swing.JButton();
        btnEditSensor = new javax.swing.JButton();
        addSensorDialog = new javax.swing.JDialog();
        jLabel20 = new javax.swing.JLabel();
        txtSensorLocation = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cmbSensorType = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        txtSensorFrequency = new javax.swing.JSpinner();
        btnSensorAdd = new javax.swing.JButton();
        btnSensorCancel = new javax.swing.JButton();
        chkSensorEnabled = new javax.swing.JCheckBox();
        editSensorDialog = new javax.swing.JDialog();
        jLabel23 = new javax.swing.JLabel();
        txtEditSensorLocation = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtEditSensorFrequency = new javax.swing.JSpinner();
        btnSensorEdit = new javax.swing.JButton();
        btnSensorCancelEdit = new javax.swing.JButton();
        chkEditSensorEnabled = new javax.swing.JCheckBox();
        lblFields = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        farmList = new javax.swing.JList();
        btnFieldStationSetup = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fieldList = new javax.swing.JList();
        lblFields1 = new javax.swing.JLabel();
        btnSensorSetup = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        plotList = new javax.swing.JList();

        fieldStationDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        fieldStationDialog.setMinimumSize(new java.awt.Dimension(400, 300));
        fieldStationDialog.setPreferredSize(new java.awt.Dimension(400, 300));

        lblFieldStation.setText("Field name");

        jLabel16.setText("Location (coordinates):");

        txtFieldStationLocation.setText("53.378480, -1.429769");

        jLabel17.setText("Phone Number: ");

        jLabel18.setText("Unique Access Code:");

        btnFieldStationAdd.setText("Ok");
        btnFieldStationAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFieldStationAddActionPerformed(evt);
            }
        });

        btnFieldStationCancel.setText("Cancel");
        btnFieldStationCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFieldStationCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fieldStationDialogLayout = new javax.swing.GroupLayout(fieldStationDialog.getContentPane());
        fieldStationDialog.getContentPane().setLayout(fieldStationDialogLayout);
        fieldStationDialogLayout.setHorizontalGroup(
            fieldStationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieldStationDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fieldStationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFieldStationLocation)
                    .addComponent(txtFieldStationPhone)
                    .addComponent(txtFieldStationCode)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fieldStationDialogLayout.createSequentialGroup()
                        .addGap(0, 264, Short.MAX_VALUE)
                        .addComponent(btnFieldStationCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFieldStationAdd))
                    .addGroup(fieldStationDialogLayout.createSequentialGroup()
                        .addGroup(fieldStationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFieldStation)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        fieldStationDialogLayout.setVerticalGroup(
            fieldStationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieldStationDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFieldStation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFieldStationLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFieldStationPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFieldStationCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(fieldStationDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFieldStationAdd)
                    .addComponent(btnFieldStationCancel))
                .addContainerGap())
        );

        sensorManageDialog.setMinimumSize(new java.awt.Dimension(400, 240));
        sensorManageDialog.setPreferredSize(new java.awt.Dimension(400, 240));

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel19.setText("Sensors:");

        sensorList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sensorListMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(sensorList);

        btnAddSensor.setText("+ Add sensor");
        btnAddSensor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSensorActionPerformed(evt);
            }
        });

        btnEditSensor.setText("Edit Selected Sensor");
        btnEditSensor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSensorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sensorManageDialogLayout = new javax.swing.GroupLayout(sensorManageDialog.getContentPane());
        sensorManageDialog.getContentPane().setLayout(sensorManageDialogLayout);
        sensorManageDialogLayout.setHorizontalGroup(
            sensorManageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorManageDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sensorManageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(sensorManageDialogLayout.createSequentialGroup()
                        .addGap(0, 321, Short.MAX_VALUE)
                        .addComponent(btnClose))
                    .addGroup(sensorManageDialogLayout.createSequentialGroup()
                        .addGroup(sensorManageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(sensorManageDialogLayout.createSequentialGroup()
                                .addComponent(btnAddSensor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditSensor)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sensorManageDialogLayout.setVerticalGroup(
            sensorManageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorManageDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sensorManageDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddSensor)
                    .addComponent(btnEditSensor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClose)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        addSensorDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addSensorDialog.setTitle("Add sensor");
        addSensorDialog.setMinimumSize(new java.awt.Dimension(275, 280));
        addSensorDialog.setPreferredSize(new java.awt.Dimension(275, 280));

        jLabel20.setText("Location (coordinates):");

        txtSensorLocation.setText("53.378480, -1.429769");

        jLabel21.setText("Type:");

        cmbSensorType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Air Temperature", "Pressure", "Soil Moisture", "Acidity", "Soil Temperature", "Light Sensor" }));

        jLabel22.setText("Frequency (mins, minimum +1): ");

        btnSensorAdd.setText("Ok");
        btnSensorAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSensorAddActionPerformed(evt);
            }
        });

        btnSensorCancel.setText("Cancel");
        btnSensorCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSensorCancelActionPerformed(evt);
            }
        });

        chkSensorEnabled.setText("Enabled?");

        javax.swing.GroupLayout addSensorDialogLayout = new javax.swing.GroupLayout(addSensorDialog.getContentPane());
        addSensorDialog.getContentPane().setLayout(addSensorDialogLayout);
        addSensorDialogLayout.setHorizontalGroup(
            addSensorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addSensorDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addSensorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkSensorEnabled, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSensorLocation, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbSensorType, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSensorFrequency, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addSensorDialogLayout.createSequentialGroup()
                        .addGap(0, 139, Short.MAX_VALUE)
                        .addComponent(btnSensorCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSensorAdd))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addSensorDialogLayout.createSequentialGroup()
                        .addGroup(addSensorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        addSensorDialogLayout.setVerticalGroup(
            addSensorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addSensorDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSensorLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSensorType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSensorFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkSensorEnabled)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(addSensorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSensorAdd)
                    .addComponent(btnSensorCancel))
                .addContainerGap())
        );

        editSensorDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        editSensorDialog.setTitle("Edit sensor");
        editSensorDialog.setMinimumSize(new java.awt.Dimension(275, 232));
        editSensorDialog.setPreferredSize(new java.awt.Dimension(275, 232));

        jLabel23.setText("Location (coordinates):");

        jLabel25.setText("Frequency (mins, minimum +1) : ");

        txtEditSensorFrequency.setValue(1);

        btnSensorEdit.setText("Ok");
        btnSensorEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSensorEditActionPerformed(evt);
            }
        });

        btnSensorCancelEdit.setText("Cancel");
        btnSensorCancelEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSensorCancelEditActionPerformed(evt);
            }
        });

        chkEditSensorEnabled.setText("Enabled?");

        javax.swing.GroupLayout editSensorDialogLayout = new javax.swing.GroupLayout(editSensorDialog.getContentPane());
        editSensorDialog.getContentPane().setLayout(editSensorDialogLayout);
        editSensorDialogLayout.setHorizontalGroup(
            editSensorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSensorDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editSensorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEditSensorLocation)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editSensorDialogLayout.createSequentialGroup()
                        .addGap(0, 139, Short.MAX_VALUE)
                        .addComponent(btnSensorCancelEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSensorEdit))
                    .addComponent(chkEditSensorEnabled, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEditSensorFrequency)
                    .addGroup(editSensorDialogLayout.createSequentialGroup()
                        .addGroup(editSensorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        editSensorDialogLayout.setVerticalGroup(
            editSensorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSensorDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEditSensorLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEditSensorFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkEditSensorEnabled)
                .addGap(18, 18, 18)
                .addGroup(editSensorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSensorEdit)
                    .addComponent(btnSensorCancelEdit))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblFields.setText("Fields:");

        btnLogout.setText("Log Out");
        btnLogout.setName("btnLogout"); // NOI18N

        lblUsername.setText("username");
        lblUsername.setName("lblUsername"); // NOI18N

        farmList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                farmListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(farmList);

        btnFieldStationSetup.setText("Manage Field Station");
        btnFieldStationSetup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFieldStationSetupActionPerformed(evt);
            }
        });

        jLabel1.setText("Farms:");

        fieldList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(fieldList);

        lblFields1.setText("Sensors:");

        btnSensorSetup.setText("Manage Sensors");
        btnSensorSetup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSensorSetupActionPerformed(evt);
            }
        });

        plotList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plotListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(plotList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLogout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblUsername)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFields1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSensorSetup))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFields)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFieldStationSetup))
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3))
                        .addGap(0, 31, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(lblUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFields)
                    .addComponent(btnFieldStationSetup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFields1)
                    .addComponent(btnSensorSetup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void farmListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_farmListMouseClicked
        setSelectedFarm();
    }//GEN-LAST:event_farmListMouseClicked

    private void btnFieldStationSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFieldStationSetupActionPerformed
        if ((!fieldList.isSelectionEmpty()) && (!fieldList.isSelectionEmpty()))
        {
            if (selectedField.getName() != null)
            {
                lblFieldStation.setText(selectedField.getName() + " station setup");
            }
            if (selectedField.getFieldStation().getGPSCoord() != null)
            {
                txtFieldStationLocation.setText(selectedField.getFieldStation().getGPSCoord().toString());
            }
            if (selectedField.getFieldStation().getPhoneNumber() != null)
            {
                txtFieldStationPhone.setText(selectedField.getFieldStation().getPhoneNumber());
            }
            if (selectedField.getFieldStation().getUniqueCode() != null)
            {
                txtFieldStationCode.setText(selectedField.getFieldStation().getUniqueCode());
            }
            fieldStationDialog.setVisible(true);
        }
    }//GEN-LAST:event_btnFieldStationSetupActionPerformed

    private void fieldListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldListMouseClicked
        setSelectedField();
    }//GEN-LAST:event_fieldListMouseClicked

    private void btnFieldStationAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFieldStationAddActionPerformed
        addFieldStation();
        fieldStationDialog.dispose();
    }//GEN-LAST:event_btnFieldStationAddActionPerformed

    private void btnFieldStationCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFieldStationCancelActionPerformed
        fieldStationDialog.dispose();
    }//GEN-LAST:event_btnFieldStationCancelActionPerformed

    private void btnSensorAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSensorAddActionPerformed
        addSensor();
        sensorList.setListData(selectedPlot.getSensors().toArray());
        txtSensorLocation.setText("");
        addSensorDialog.dispose();
    }//GEN-LAST:event_btnSensorAddActionPerformed

    private void btnSensorCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSensorCancelActionPerformed
        addSensorDialog.dispose();
    }//GEN-LAST:event_btnSensorCancelActionPerformed

    private void btnSensorSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSensorSetupActionPerformed
        if ((!plotList.isSelectionEmpty()) && (!plotList.isSelectionEmpty()))
        {
            sensors = selectedPlot.getSensors();
            sensorList.setListData(selectedPlot.getSensors().toArray());
            sensorManageDialog.setVisible(true);
        }
    }//GEN-LAST:event_btnSensorSetupActionPerformed

    private void plotListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plotListMouseClicked
        setSelectedPlot();
    }//GEN-LAST:event_plotListMouseClicked

    private void btnAddSensorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSensorActionPerformed
        addSensorDialog.setVisible(true);
    }//GEN-LAST:event_btnAddSensorActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        sensorManageDialog.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnEditSensorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSensorActionPerformed
        if ((!sensorList.isSelectionEmpty()) && (!sensorList.isSelectionEmpty()))
        {
            editSensorDialog.setVisible(true);
            txtEditSensorLocation.setText(selectedSensor.getCoord().toString());
            txtEditSensorFrequency.setValue((int) selectedSensor.getFrequency());
            chkEditSensorEnabled.setSelected(selectedSensor.isEnabled());
        }
    }//GEN-LAST:event_btnEditSensorActionPerformed

    private void sensorListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sensorListMouseClicked
        setSelectedSensor();
    }//GEN-LAST:event_sensorListMouseClicked

    private void btnSensorEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSensorEditActionPerformed
        editSensor();
        sensorList.setListData(selectedPlot.getSensors().toArray());
        editSensorDialog.dispose();
    }//GEN-LAST:event_btnSensorEditActionPerformed

    private void btnSensorCancelEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSensorCancelEditActionPerformed
        editSensorDialog.dispose();
    }//GEN-LAST:event_btnSensorCancelEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog addSensorDialog;
    private javax.swing.JButton btnAddSensor;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnEditSensor;
    private javax.swing.JButton btnFieldStationAdd;
    private javax.swing.JButton btnFieldStationCancel;
    private javax.swing.JButton btnFieldStationSetup;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSensorAdd;
    private javax.swing.JButton btnSensorCancel;
    private javax.swing.JButton btnSensorCancelEdit;
    private javax.swing.JButton btnSensorEdit;
    private javax.swing.JButton btnSensorSetup;
    private javax.swing.JCheckBox chkEditSensorEnabled;
    private javax.swing.JCheckBox chkSensorEnabled;
    private javax.swing.JComboBox<String> cmbSensorType;
    private javax.swing.JDialog editSensorDialog;
    private javax.swing.JList farmList;
    private javax.swing.JList fieldList;
    private javax.swing.JDialog fieldStationDialog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblFieldStation;
    private javax.swing.JLabel lblFields;
    private javax.swing.JLabel lblFields1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList plotList;
    private javax.swing.JList sensorList;
    private javax.swing.JDialog sensorManageDialog;
    private javax.swing.JSpinner txtEditSensorFrequency;
    private javax.swing.JTextField txtEditSensorLocation;
    private javax.swing.JTextField txtFieldStationCode;
    private javax.swing.JTextField txtFieldStationLocation;
    private javax.swing.JTextField txtFieldStationPhone;
    private javax.swing.JSpinner txtSensorFrequency;
    private javax.swing.JTextField txtSensorLocation;
    // End of variables declaration//GEN-END:variables
}
