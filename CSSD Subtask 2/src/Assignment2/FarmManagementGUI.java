/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import javax.swing.DefaultListModel;

/**
 *
 * @author b4018943
 */
public class FarmManagementGUI extends javax.swing.JFrame 
{
    User user = null;
    FarmList farms = new FarmList();
    FieldList fields = new FieldList();
    Farm selectedFarm = null;
    Farm addToFarm = null;
    Field selectedField = null;
    Plot selectedPlot = null;
    DefaultListModel farmCoords;
    DefaultListModel fieldCoords;
    DefaultListModel plotCoords;
    DefaultListModel sensorDataModel = new DefaultListModel();
    
    /**
     * Creates new form FarmList
     * @param user
     */
    public FarmManagementGUI(User user) 
    {
        initComponents();
        this.user = user;
        farms = user.getFarms();
        lblUsername.setText(user.getUsername());
        getFarmList();
    }
    
    //fill farm JList
    public final void getFarmList()
    {
        farmList.setListData(farms.toArray());
    }
    
    //fill field JList based on selected farm
    public final void getFieldList()
    {
        fieldList.setListData(fields.toArray());
    }
    
    //fill field JList based on selected field
    public final void getPlotList()
    {
        plotList.setListData(selectedField.getPlots().toArray());
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
                break;
            }
        }
    }
    
    public void setSelectedPlot()
    {
        int index = plotList.getSelectedIndex();
        for (int i = 0; i < selectedField.getPlots().size(); i++)
        {
            selectedPlot = selectedField.getPlots().get(i);
            if (selectedPlot.getName().equals(plotList.getModel().getElementAt(index).toString()))
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
    
    public void addFarm()
    {
        farmCoords = new DefaultListModel();
        farmCoordList.setModel(farmCoords);
        addFarmDialog.setVisible(true);
    }
    
    public void addField()
    {
        if ((!farmList.isSelectionEmpty()) && (!farmList.isSelectionEmpty()))
        {
            fieldCoords = new DefaultListModel();
            farmCoordList.setModel(fieldCoords);
            addToFarm = selectedFarm;
            addFieldDialog.setVisible(true);
        }else{
            //show error
        }
    }
    
    public void newFarm()
    {
        GPSCoordList location = new GPSCoordList();
        String farmName = txtFarmName.getText();
        for (int i=0; i < farmCoordList.getModel().getSize(); i++)
        {
            String[] coords = farmCoordList.getModel().getElementAt(i).split(",");
            GPSCoord gps = new GPSCoord(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
            location.add(gps);
        }
        Area area = new Area(location);
        Farm farm = new Farm(area, farmName);
        user.addFarm(farm);
        getFarmList();
        addFarmDialog.dispose();
    }
    
    public void newField()
    {
        GPSCoordList location = new GPSCoordList();
        String fieldName = txtFieldName.getText();
        for (int i=0; i < fieldCoordList.getModel().getSize(); i++)
        {
            String[] coords = fieldCoordList.getModel().getElementAt(i).split(",");
            GPSCoord gps = new GPSCoord(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
            location.add(gps);
        }
        Area area = new Area(location);
        Field field = new Field(area, fieldName);
        addToFarm.getFields().addField(field);
        getFieldList();
        addFieldDialog.dispose();
    }
    
    public void newPlot()
    {
        GPSCoordList location = new GPSCoordList();
        String plotName = txtPlotName.getText();
        Crop crop = new Crop(txtPlotCrop.getText());
        String plotStateTxt = cmbPlotState.getSelectedItem().toString();
        PlotState plotState;
        switch (plotStateTxt) {
            case "Empty":  plotState = PlotState.EMPTY;
                     break;
            case "Sprouted":  plotState = PlotState.SPROUTED;
                     break;
            case "Planted":  plotState = PlotState.PLANTED;
                     break;
            case "Ready to harvest":  plotState = PlotState.READY_TO_HARVEST;
                     break;
            default: plotState = PlotState.EMPTY;
                     break;
        }
        for (int i=0; i < plotCoordList.getModel().getSize(); i++)
        {
            String[] coords = plotCoordList.getModel().getElementAt(i).split(",");
            GPSCoord gps = new GPSCoord(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
            location.add(gps);
        }
        Area area = new Area(location);
        Plot plot = new Plot(plotName, area, plotState, crop);
        selectedField.getPlots().add(plot);
        getPlotList();
        addPlotDialog.dispose();
    }
    
    public void deleteFarm()
    {
        if ((!farmList.isSelectionEmpty()) && (!farmList.isSelectionEmpty()))
        {
            user.getFarms().removeFarm(selectedFarm);
            getFarmList();
            fieldList.setListData(new String[0]);
        }
    }
    
    public void deleteField()
    {
        if ((!fieldList.isSelectionEmpty()) && (!fieldList.isSelectionEmpty()))
        {
            fields.removeField(selectedField);
            getFieldList();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addFarmDialog = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        btnFarmDialogAdd = new javax.swing.JButton();
        btnFarmDialogCancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtFarmName = new javax.swing.JTextField();
        txtFarmLocation = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        farmCoordList = new javax.swing.JList<>();
        btnAddFarmCoords = new javax.swing.JButton();
        addFieldDialog = new javax.swing.JDialog();
        txtFieldLocation = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        fieldCoordList = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        btnFieldDialogAdd = new javax.swing.JButton();
        btnFieldDialogCancel = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtFieldName = new javax.swing.JTextField();
        btnAddFieldCoords = new javax.swing.JButton();
        viewFarmDialog = new javax.swing.JDialog();
        lblFarmName = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        farmLocationList = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        viewFieldDialog = new javax.swing.JDialog();
        lblFieldName = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        fieldLocationList = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        plotList = new javax.swing.JList();
        btnAddPlot = new javax.swing.JButton();
        btnRemovePlot = new javax.swing.JButton();
        btnViewPlot = new javax.swing.JButton();
        addPlotDialog = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        txtPlotName = new javax.swing.JTextField();
        btnAddPlotCoords = new javax.swing.JButton();
        txtPlotLocation = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        plotCoordList = new javax.swing.JList<>();
        jLabel13 = new javax.swing.JLabel();
        btnPlotDialogAdd = new javax.swing.JButton();
        btnPlotDialogCancel = new javax.swing.JButton();
        txtPlotCrop = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbPlotState = new javax.swing.JComboBox<>();
        viewPlotDialog = new javax.swing.JDialog();
        jScrollPane9 = new javax.swing.JScrollPane();
        sensorDataList = new javax.swing.JList(sensorDataModel);
        btnLogout = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        btnAddFarm = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        farmList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fieldList = new javax.swing.JList();
        lblFields = new javax.swing.JLabel();
        btnDeleteFarm = new javax.swing.JButton();
        btnDeleteField = new javax.swing.JButton();
        btnAddField = new javax.swing.JButton();
        btnViewFarm = new javax.swing.JButton();
        btnViewField = new javax.swing.JButton();

        addFarmDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addFarmDialog.setAlwaysOnTop(true);
        addFarmDialog.setMinimumSize(new java.awt.Dimension(280, 340));
        addFarmDialog.setModal(true);
        addFarmDialog.setResizable(false);

        jLabel2.setText("Add New Farm");

        btnFarmDialogAdd.setText("Ok");
        btnFarmDialogAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFarmDialogAddActionPerformed(evt);
            }
        });

        btnFarmDialogCancel.setText("Cancel");
        btnFarmDialogCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFarmDialogCancelActionPerformed(evt);
            }
        });

        jLabel3.setText("Farm Name:");

        txtFarmLocation.setText("53.378480, -1.429769");

        jLabel4.setText("Add new farm co-ordinate (location):");

        jScrollPane4.setViewportView(farmCoordList);

        btnAddFarmCoords.setText("+ Add to co-ordinates");
        btnAddFarmCoords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFarmCoordsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addFarmDialogLayout = new javax.swing.GroupLayout(addFarmDialog.getContentPane());
        addFarmDialog.getContentPane().setLayout(addFarmDialogLayout);
        addFarmDialogLayout.setHorizontalGroup(
            addFarmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFarmDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addFarmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(txtFarmName)
                    .addComponent(txtFarmLocation)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addFarmDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFarmDialogCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFarmDialogAdd))
                    .addGroup(addFarmDialogLayout.createSequentialGroup()
                        .addGroup(addFarmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(btnAddFarmCoords))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        addFarmDialogLayout.setVerticalGroup(
            addFarmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFarmDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFarmName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFarmLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddFarmCoords)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addFarmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFarmDialogCancel)
                    .addComponent(btnFarmDialogAdd))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        addFieldDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addFieldDialog.setMinimumSize(new java.awt.Dimension(280, 340));
        addFieldDialog.setResizable(false);

        txtFieldLocation.setText("53.378480, -1.429769");
        txtFieldLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldLocationActionPerformed(evt);
            }
        });

        jLabel5.setText("Add new field co-ordinate (location):");

        jScrollPane5.setViewportView(fieldCoordList);

        jLabel6.setText("Add New Field");

        btnFieldDialogAdd.setText("Ok");
        btnFieldDialogAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFieldDialogAddActionPerformed(evt);
            }
        });

        btnFieldDialogCancel.setText("Cancel");
        btnFieldDialogCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFieldDialogCancelActionPerformed(evt);
            }
        });

        jLabel7.setText("Field Name:");

        btnAddFieldCoords.setText("+ Add to co-ordinates");
        btnAddFieldCoords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFieldCoordsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addFieldDialogLayout = new javax.swing.GroupLayout(addFieldDialog.getContentPane());
        addFieldDialog.getContentPane().setLayout(addFieldDialogLayout);
        addFieldDialogLayout.setHorizontalGroup(
            addFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFieldDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addFieldDialogLayout.createSequentialGroup()
                        .addGroup(addFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(btnAddFieldCoords))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addFieldDialogLayout.createSequentialGroup()
                        .addGroup(addFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFieldName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldLocation, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addContainerGap(1, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addFieldDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFieldDialogCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFieldDialogAdd)
                .addContainerGap())
        );
        addFieldDialogLayout.setVerticalGroup(
            addFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFieldDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFieldLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnAddFieldCoords)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFieldDialogCancel)
                    .addComponent(btnFieldDialogAdd))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        viewFarmDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewFarmDialog.setMinimumSize(new java.awt.Dimension(300, 300));

        lblFarmName.setText("Farm Name");

        jScrollPane3.setViewportView(farmLocationList);

        jLabel8.setText("Location Coordinates: ");

        javax.swing.GroupLayout viewFarmDialogLayout = new javax.swing.GroupLayout(viewFarmDialog.getContentPane());
        viewFarmDialog.getContentPane().setLayout(viewFarmDialogLayout);
        viewFarmDialogLayout.setHorizontalGroup(
            viewFarmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewFarmDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewFarmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addGroup(viewFarmDialogLayout.createSequentialGroup()
                        .addGroup(viewFarmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFarmName)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        viewFarmDialogLayout.setVerticalGroup(
            viewFarmDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewFarmDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFarmName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );

        viewFieldDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewFieldDialog.setMinimumSize(new java.awt.Dimension(500, 400));

        lblFieldName.setText("Field Name");

        jScrollPane6.setViewportView(fieldLocationList);

        jLabel9.setText("Field Location Coordinates:");

        jLabel10.setText("Plots:");

        plotList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plotListMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(plotList);

        btnAddPlot.setText("+ Add Plot");
        btnAddPlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPlotActionPerformed(evt);
            }
        });

        btnRemovePlot.setText("- Remove Plot");
        btnRemovePlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemovePlotActionPerformed(evt);
            }
        });

        btnViewPlot.setText("View Plot");
        btnViewPlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewPlotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewFieldDialogLayout = new javax.swing.GroupLayout(viewFieldDialog.getContentPane());
        viewFieldDialog.getContentPane().setLayout(viewFieldDialogLayout);
        viewFieldDialogLayout.setHorizontalGroup(
            viewFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewFieldDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane7)
                    .addGroup(viewFieldDialogLayout.createSequentialGroup()
                        .addGroup(viewFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFieldName)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addGroup(viewFieldDialogLayout.createSequentialGroup()
                                .addComponent(btnAddPlot)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemovePlot)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnViewPlot)))
                        .addGap(0, 211, Short.MAX_VALUE)))
                .addContainerGap())
        );
        viewFieldDialogLayout.setVerticalGroup(
            viewFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewFieldDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFieldName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewFieldDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddPlot)
                    .addComponent(btnViewPlot)
                    .addComponent(btnRemovePlot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );

        addPlotDialog.setMinimumSize(new java.awt.Dimension(400, 470));

        jLabel11.setText("Plot Name:");

        btnAddPlotCoords.setText("+ Add to co-ordinates");
        btnAddPlotCoords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPlotCoordsActionPerformed(evt);
            }
        });

        txtPlotLocation.setText("53.378480, -1.429769");
        txtPlotLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlotLocationActionPerformed(evt);
            }
        });

        jLabel12.setText("Add new plot co-ordinate (location) - example already entered:");

        jScrollPane8.setViewportView(plotCoordList);

        jLabel13.setText("Add New Plot");

        btnPlotDialogAdd.setText("Ok");
        btnPlotDialogAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlotDialogAddActionPerformed(evt);
            }
        });

        btnPlotDialogCancel.setText("Cancel");
        btnPlotDialogCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlotDialogCancelActionPerformed(evt);
            }
        });

        jLabel14.setText("Current Crop:");

        jLabel15.setText("Plot State:");

        cmbPlotState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empty", "Planted", "Sprouted", "Ready to harvest" }));

        javax.swing.GroupLayout addPlotDialogLayout = new javax.swing.GroupLayout(addPlotDialog.getContentPane());
        addPlotDialog.getContentPane().setLayout(addPlotDialogLayout);
        addPlotDialogLayout.setHorizontalGroup(
            addPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPlotDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPlotDialogLayout.createSequentialGroup()
                        .addComponent(txtPlotName)
                        .addContainerGap(1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addPlotDialogLayout.createSequentialGroup()
                        .addGroup(addPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12)
                            .addComponent(btnAddPlotCoords)
                            .addComponent(jLabel15))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPlotDialogLayout.createSequentialGroup()
                        .addGroup(addPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                            .addComponent(txtPlotLocation, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPlotState, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPlotCrop, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addPlotDialogLayout.createSequentialGroup()
                                .addGroup(addPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPlotDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPlotDialogCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlotDialogAdd)
                .addContainerGap())
        );
        addPlotDialogLayout.setVerticalGroup(
            addPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPlotDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlotName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlotCrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addGap(11, 11, 11)
                .addComponent(cmbPlotState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlotLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnAddPlotCoords)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlotDialogCancel)
                    .addComponent(btnPlotDialogAdd))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        viewPlotDialog.setMinimumSize(new java.awt.Dimension(600, 500));

        jScrollPane9.setViewportView(sensorDataList);

        javax.swing.GroupLayout viewPlotDialogLayout = new javax.swing.GroupLayout(viewPlotDialog.getContentPane());
        viewPlotDialog.getContentPane().setLayout(viewPlotDialogLayout);
        viewPlotDialogLayout.setHorizontalGroup(
            viewPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewPlotDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                .addContainerGap())
        );
        viewPlotDialogLayout.setVerticalGroup(
            viewPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPlotDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(371, Short.MAX_VALUE))
        );

        btnLogout.setText("Log Out");
        btnLogout.setName("btnLogout"); // NOI18N

        lblUsername.setText("username");
        lblUsername.setName("lblUsername"); // NOI18N

        btnAddFarm.setText("+ Add Farm");
        btnAddFarm.setName("btnAddFarm"); // NOI18N
        btnAddFarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFarmActionPerformed(evt);
            }
        });

        farmList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                farmListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(farmList);

        jLabel1.setText("Farms:");

        fieldList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fieldListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(fieldList);

        lblFields.setText("Fields:");

        btnDeleteFarm.setText("- Delete Selected Farm");
        btnDeleteFarm.setName("btnAddFarm"); // NOI18N
        btnDeleteFarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFarmActionPerformed(evt);
            }
        });

        btnDeleteField.setText("- Delete Selected Field");
        btnDeleteField.setName("btnAddFarm"); // NOI18N
        btnDeleteField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFieldActionPerformed(evt);
            }
        });

        btnAddField.setText("+ Add Field To Selected Farm");
        btnAddField.setName("btnAddFarm"); // NOI18N
        btnAddField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFieldActionPerformed(evt);
            }
        });

        btnViewFarm.setText("View Farm");
        btnViewFarm.setName("btnAddFarm"); // NOI18N
        btnViewFarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewFarmActionPerformed(evt);
            }
        });

        btnViewField.setText("View Field");
        btnViewField.setName("btnAddFarm"); // NOI18N
        btnViewField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewFieldActionPerformed(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnAddField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnViewField))
                            .addComponent(lblFields, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnAddFarm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteFarm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnViewFarm))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 30, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout)
                    .addComponent(lblUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddFarm)
                    .addComponent(btnDeleteFarm)
                    .addComponent(btnViewFarm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteField)
                    .addComponent(btnAddField)
                    .addComponent(btnViewField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(lblFields)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void farmListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_farmListMouseClicked
        setSelectedFarm();
    }//GEN-LAST:event_farmListMouseClicked

    private void btnDeleteFarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFarmActionPerformed
        deleteFarm();
    }//GEN-LAST:event_btnDeleteFarmActionPerformed

    private void btnDeleteFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFieldActionPerformed
        deleteField();
    }//GEN-LAST:event_btnDeleteFieldActionPerformed

    private void btnAddFarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFarmActionPerformed
        addFarm();
    }//GEN-LAST:event_btnAddFarmActionPerformed

    private void btnAddFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFieldActionPerformed
        addField();
    }//GEN-LAST:event_btnAddFieldActionPerformed

    private void btnFarmDialogCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFarmDialogCancelActionPerformed
        addFarmDialog.dispose();
    }//GEN-LAST:event_btnFarmDialogCancelActionPerformed

    private void btnFieldDialogCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFieldDialogCancelActionPerformed
        addFieldDialog.dispose();
    }//GEN-LAST:event_btnFieldDialogCancelActionPerformed

    private void btnFieldDialogAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFieldDialogAddActionPerformed
        newField();
    }//GEN-LAST:event_btnFieldDialogAddActionPerformed

    private void btnFarmDialogAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFarmDialogAddActionPerformed
        newFarm();
    }//GEN-LAST:event_btnFarmDialogAddActionPerformed

    private void btnAddFarmCoordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFarmCoordsActionPerformed
        farmCoords.addElement(txtFarmLocation.getText());
        txtFarmLocation.setText("");
        txtFarmLocation.grabFocus();
        farmCoordList.setModel(farmCoords);
    }//GEN-LAST:event_btnAddFarmCoordsActionPerformed

    private void btnAddFieldCoordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFieldCoordsActionPerformed
        fieldCoords.addElement(txtFieldLocation.getText());
        txtFieldLocation.setText("");
        txtFieldLocation.grabFocus();
        fieldCoordList.setModel(fieldCoords);
    }//GEN-LAST:event_btnAddFieldCoordsActionPerformed

    private void fieldListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fieldListMouseClicked
        setSelectedField();
    }//GEN-LAST:event_fieldListMouseClicked

    private void btnViewFarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewFarmActionPerformed
        if ((!farmList.isSelectionEmpty()) && (!farmList.isSelectionEmpty()))
        {
            lblFarmName.setText(selectedFarm.getName());
            Area area = selectedFarm.getArea();
            farmLocationList.setListData(area.getCoordList().toArray());
            viewFarmDialog.setVisible(true);
        }
    }//GEN-LAST:event_btnViewFarmActionPerformed

    private void btnViewFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewFieldActionPerformed
        if ((!fieldList.isSelectionEmpty()) && (!fieldList.isSelectionEmpty()))
        {
            lblFieldName.setText(selectedFarm.getName() + " - " + selectedField.getName());
            Area area = selectedField.getArea();
            fieldLocationList.setListData(area.getCoordList().toArray());
            getPlotList();
            viewFieldDialog.setVisible(true);
        }
    }//GEN-LAST:event_btnViewFieldActionPerformed

    private void txtFieldLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldLocationActionPerformed

    private void btnAddPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPlotActionPerformed
        plotCoords = new DefaultListModel();
        plotCoordList.setModel(plotCoords);
        addPlotDialog.setVisible(true);
    }//GEN-LAST:event_btnAddPlotActionPerformed

    private void btnRemovePlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemovePlotActionPerformed
        if ((!plotList.isSelectionEmpty()) && (!plotList.isSelectionEmpty()))
        {
            selectedField.getPlots().removePlot(selectedPlot);
            getPlotList();
        }
    }//GEN-LAST:event_btnRemovePlotActionPerformed
    
    private void btnViewPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPlotActionPerformed
        if ((!plotList.isSelectionEmpty()) && (!plotList.isSelectionEmpty()))
        {
            sensorDataModel = new DefaultListModel();
            Sensor sensor;
            SensorData sensorData;
            for (int i = 0; i < selectedPlot.getSensors().size(); i++)
            {
                sensor = selectedPlot.getSensors().get(i);
                for (int j = 0; j < sensor.getSensorDataList().size(); j++)
                {
                    sensorData = sensor.getSensorDataList().get(j);
                    //System.out.println(sensorData);
                    sensorDataModel.addElement(sensorData.toString());
                    //System.out.println(sensorDataModel.getSize());
                }
            }
            sensorDataList.setModel(sensorDataModel);
            viewPlotDialog.setVisible(true);
        }
    }//GEN-LAST:event_btnViewPlotActionPerformed

    private void plotListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plotListMouseClicked
        setSelectedPlot();
    }//GEN-LAST:event_plotListMouseClicked

    private void btnAddPlotCoordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPlotCoordsActionPerformed
        plotCoords.addElement(txtPlotLocation.getText());
        txtPlotLocation.setText("");
        txtPlotLocation.grabFocus();
        plotCoordList.setModel(plotCoords);
    }//GEN-LAST:event_btnAddPlotCoordsActionPerformed

    private void txtPlotLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlotLocationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlotLocationActionPerformed

    private void btnPlotDialogAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlotDialogAddActionPerformed
        newPlot();
    }//GEN-LAST:event_btnPlotDialogAddActionPerformed

    private void btnPlotDialogCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlotDialogCancelActionPerformed
        addPlotDialog.dispose();
    }//GEN-LAST:event_btnPlotDialogCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog addFarmDialog;
    private javax.swing.JDialog addFieldDialog;
    private javax.swing.JDialog addPlotDialog;
    private javax.swing.JButton btnAddFarm;
    private javax.swing.JButton btnAddFarmCoords;
    private javax.swing.JButton btnAddField;
    private javax.swing.JButton btnAddFieldCoords;
    private javax.swing.JButton btnAddPlot;
    private javax.swing.JButton btnAddPlotCoords;
    private javax.swing.JButton btnDeleteFarm;
    private javax.swing.JButton btnDeleteField;
    private javax.swing.JButton btnFarmDialogAdd;
    private javax.swing.JButton btnFarmDialogCancel;
    private javax.swing.JButton btnFieldDialogAdd;
    private javax.swing.JButton btnFieldDialogCancel;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPlotDialogAdd;
    private javax.swing.JButton btnPlotDialogCancel;
    private javax.swing.JButton btnRemovePlot;
    private javax.swing.JButton btnViewFarm;
    private javax.swing.JButton btnViewField;
    private javax.swing.JButton btnViewPlot;
    private javax.swing.JComboBox<String> cmbPlotState;
    private javax.swing.JList<String> farmCoordList;
    private javax.swing.JList farmList;
    private javax.swing.JList farmLocationList;
    private javax.swing.JList<String> fieldCoordList;
    private javax.swing.JList fieldList;
    private javax.swing.JList fieldLocationList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblFarmName;
    private javax.swing.JLabel lblFieldName;
    private javax.swing.JLabel lblFields;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList<String> plotCoordList;
    private javax.swing.JList plotList;
    private javax.swing.JList sensorDataList;
    private javax.swing.JTextField txtFarmLocation;
    private javax.swing.JTextField txtFarmName;
    private javax.swing.JTextField txtFieldLocation;
    private javax.swing.JTextField txtFieldName;
    private javax.swing.JTextField txtPlotCrop;
    private javax.swing.JTextField txtPlotLocation;
    private javax.swing.JTextField txtPlotName;
    private javax.swing.JDialog viewFarmDialog;
    private javax.swing.JDialog viewFieldDialog;
    private javax.swing.JDialog viewPlotDialog;
    // End of variables declaration//GEN-END:variables
}
