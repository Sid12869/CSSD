/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultListModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Implements all functionality of the Farm Management portion of the GUI.
 * Includes: add amd delete farm/field/plot, change JList for fields depending 
 * on selected farm, show all plots in new dialog, view the plot's sensor data.
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
    
    DefaultListModel airTempDataModel = new DefaultListModel();
    DefaultListModel pressureDataModel = new DefaultListModel();
    DefaultListModel soilTempDataModel = new DefaultListModel();
    DefaultListModel soilAcidityDataModel = new DefaultListModel();
    DefaultListModel soilMoistureDataModel = new DefaultListModel();
    DefaultListModel lightDataModel = new DefaultListModel();
    
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
    
    //Set selected farm from the JList
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
    
    //Set selected field from the JList
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
    
    //Set selected plot from the JList
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
    
    //Get list of fields from selected farm in JList
    public void showFarmFields()
    {
        fields = selectedFarm.getFields();
        fieldList.setListData(fields.toArray());
    }
    
    //show farm add dialog GUI
    public void addFarm()
    {
        farmCoords = new DefaultListModel();
        farmCoordList.setModel(farmCoords);
        addFarmDialog.setVisible(true);
    }
    
    //show field add dialog GUI
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
    
    //add a new farm to FarmList in user
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
    
    //add a new field to FieldList in farm
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
    
    //add a new plot to PlotList in field
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
    
    //remove farm
    public void deleteFarm()
    {
        if ((!farmList.isSelectionEmpty()) && (!farmList.isSelectionEmpty()))
        {
            user.getFarms().removeFarm(selectedFarm);
            getFarmList();
            fieldList.setListData(new String[0]);
        }
    }
    
    //remove field
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        airTempDataList = new javax.swing.JList(airTempDataModel);
        jLabel16 = new javax.swing.JLabel();
        btnAirTempChart = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnPressureChart = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        pressureDataList = new javax.swing.JList(pressureDataModel);
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnSoilMoistureChart = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        soilMoistureDataList = new javax.swing.JList(pressureDataModel);
        jLabel18 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnSoilTempChart = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        soilTempDataList = new javax.swing.JList(pressureDataModel);
        jLabel19 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnAcidityChart = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        acidityDataList = new javax.swing.JList(pressureDataModel);
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnLightChart = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        lightDataList = new javax.swing.JList(pressureDataModel);
        jLabel21 = new javax.swing.JLabel();
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

        viewPlotDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewPlotDialog.setMinimumSize(new java.awt.Dimension(600, 420));
        viewPlotDialog.setPreferredSize(new java.awt.Dimension(600, 420));

        jScrollPane9.setViewportView(airTempDataList);

        jLabel16.setText("List of data for plot:");

        btnAirTempChart.setText("Generate chart for data");
        btnAirTempChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAirTempChartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAirTempChart))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAirTempChart)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Air Temperature", jPanel1);

        btnPressureChart.setText("Generate chart for data");
        btnPressureChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPressureChartActionPerformed(evt);
            }
        });

        jScrollPane10.setViewportView(pressureDataList);

        jLabel17.setText("List of data for plot:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPressureChart))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPressureChart)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Pressure", jPanel2);

        btnSoilMoistureChart.setText("Generate chart for data");
        btnSoilMoistureChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSoilMoistureChartActionPerformed(evt);
            }
        });

        jScrollPane11.setViewportView(soilMoistureDataList);

        jLabel18.setText("List of data for plot:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSoilMoistureChart))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSoilMoistureChart)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Soil Moisture", jPanel3);

        btnSoilTempChart.setText("Generate chart for data");
        btnSoilTempChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSoilTempChartActionPerformed(evt);
            }
        });

        jScrollPane12.setViewportView(soilTempDataList);

        jLabel19.setText("List of data for plot:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSoilTempChart))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSoilTempChart)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Soil Temperature", jPanel4);

        btnAcidityChart.setText("Generate chart for data");
        btnAcidityChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcidityChartActionPerformed(evt);
            }
        });

        jScrollPane13.setViewportView(acidityDataList);

        jLabel20.setText("List of data for plot:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAcidityChart))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAcidityChart)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Soil Acidity", jPanel5);

        btnLightChart.setText("Generate chart for data");
        btnLightChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLightChartActionPerformed(evt);
            }
        });

        jScrollPane14.setViewportView(lightDataList);

        jLabel21.setText("List of data for plot:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLightChart))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLightChart)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Light", jPanel6);

        javax.swing.GroupLayout viewPlotDialogLayout = new javax.swing.GroupLayout(viewPlotDialog.getContentPane());
        viewPlotDialog.getContentPane().setLayout(viewPlotDialogLayout);
        viewPlotDialogLayout.setHorizontalGroup(
            viewPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPlotDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        viewPlotDialogLayout.setVerticalGroup(
            viewPlotDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPlotDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
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
    
    //sets up each JList with appropriate sensor data based on sensor type
    private void btnViewPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewPlotActionPerformed
        if ((!plotList.isSelectionEmpty()) && (!plotList.isSelectionEmpty()))
        {
            airTempDataModel = new DefaultListModel();
            pressureDataModel = new DefaultListModel();
            soilTempDataModel = new DefaultListModel();
            soilAcidityDataModel = new DefaultListModel();
            soilMoistureDataModel = new DefaultListModel();
            lightDataModel = new DefaultListModel();
            
            Sensor sensor;
            SensorData sensorData;
            for (int i = 0; i < selectedPlot.getSensors().size(); i++)
            {
                sensor = selectedPlot.getSensors().get(i);
                for (int j = 0; j < sensor.getSensorDataList().size(); j++)
                {
                    sensorData = sensor.getSensorDataList().get(j);
                    if (sensor.getSensorType() == SensorType.AIR_TEMPERATURE)
                    {
                        airTempDataModel.addElement(sensorData.toString());
                    }
                    else if(sensor.getSensorType() == SensorType.PRESSURE)
                    {
                        pressureDataModel.addElement(sensorData.toString());
                    }
                    else if(sensor.getSensorType() == SensorType.SOIL_TEMPERATURE)
                    {
                        soilTempDataModel.addElement(sensorData.toString());
                    }
                    else if(sensor.getSensorType() == SensorType.ACIDITY)
                    {
                        soilAcidityDataModel.addElement(sensorData.toString());
                    }
                    else if(sensor.getSensorType() == SensorType.SOIL_MOISTURE)
                    {
                        soilMoistureDataModel.addElement(sensorData.toString());
                    }
                    else if(sensor.getSensorType() == SensorType.LIGHT_SENSOR)
                    {
                        lightDataModel.addElement(sensorData.toString());
                    }
                }
            }
            airTempDataList.setModel(airTempDataModel);
            pressureDataList.setModel(pressureDataModel);
            soilTempDataList.setModel(soilTempDataModel);
            acidityDataList.setModel(soilAcidityDataModel);
            soilMoistureDataList.setModel(soilMoistureDataModel);
            lightDataList.setModel(lightDataModel);
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
    
    //following functions are all the same code but show different sensor type
    //chart data depending on which button is clicked
    private void btnAirTempChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAirTempChartActionPerformed
        //Set up chart data/key
        XYSeries signal = new XYSeries("amount");
        XYSeriesCollection dataset = new XYSeriesCollection(signal);

        JFrame f = new JFrame(selectedPlot.getName() + " air temperature chart"); //set up new JFrame
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //dispose frame on close
        JFreeChart chart = ChartFactory.createXYLineChart(selectedPlot.getName(), "Reading number", "Reading", dataset); //create chart and add data/keys
        f.add(new ChartPanel(chart)); //add chart to frame
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        signal.clear();
        Sensor sensor;
        SensorData sensorData;
        //For each sensor in selected plot:
        for (int i = 0; i < selectedPlot.getSensors().size(); i++)
        {
            sensor = selectedPlot.getSensors().get(i);
            if (sensor.getSensorType() == SensorType.AIR_TEMPERATURE)
            {
                //For each sensor data in sensor:
                for (int j = 0; j < sensor.getSensorDataList().size(); j++)
                {
                    sensorData = sensor.getSensorDataList().get(j);
                    signal.add(j, sensorData.getDataRepresentationType()); //add the data to the chart
                }
            }
        }
        //set up frame and show:
        f.add(p, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }//GEN-LAST:event_btnAirTempChartActionPerformed

    private void btnPressureChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPressureChartActionPerformed
        XYSeries signal = new XYSeries("amount");
        XYSeriesCollection dataset = new XYSeriesCollection(signal);

        JFrame f = new JFrame(selectedPlot.getName() + " pressure sensor chart");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFreeChart chart = ChartFactory.createXYLineChart(selectedPlot.getName(), "Reading number", "Reading", dataset);
        f.add(new ChartPanel(chart));
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        signal.clear();
        Sensor sensor;
        SensorData sensorData;
        for (int i = 0; i < selectedPlot.getSensors().size(); i++)
        {
            sensor = selectedPlot.getSensors().get(i);
            if (sensor.getSensorType() == SensorType.PRESSURE)
            {
                for (int j = 0; j < sensor.getSensorDataList().size(); j++)
                {
                    sensorData = sensor.getSensorDataList().get(j);
                    signal.add(j, sensorData.getDataRepresentationType());
                }
            }
        }

        f.add(p, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }//GEN-LAST:event_btnPressureChartActionPerformed

    private void btnSoilMoistureChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoilMoistureChartActionPerformed
        XYSeries signal = new XYSeries("amount");
        XYSeriesCollection dataset = new XYSeriesCollection(signal);

        JFrame f = new JFrame(selectedPlot.getName() + " soil moisture sensor chart");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFreeChart chart = ChartFactory.createXYLineChart(selectedPlot.getName(), "Reading number", "Reading", dataset);
        f.add(new ChartPanel(chart));
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        signal.clear();
        Sensor sensor;
        SensorData sensorData;
        for (int i = 0; i < selectedPlot.getSensors().size(); i++)
        {
            sensor = selectedPlot.getSensors().get(i);
            if (sensor.getSensorType() == SensorType.SOIL_MOISTURE)
            {
                for (int j = 0; j < sensor.getSensorDataList().size(); j++)
                {
                    sensorData = sensor.getSensorDataList().get(j);
                    signal.add(j, sensorData.getDataRepresentationType());
                }
            }
        }

        f.add(p, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }//GEN-LAST:event_btnSoilMoistureChartActionPerformed

    private void btnSoilTempChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoilTempChartActionPerformed
        XYSeries signal = new XYSeries("amount");
        XYSeriesCollection dataset = new XYSeriesCollection(signal);

        JFrame f = new JFrame(selectedPlot.getName() + " soil temperature sensor chart");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFreeChart chart = ChartFactory.createXYLineChart(selectedPlot.getName(), "Reading number", "Reading", dataset);
        f.add(new ChartPanel(chart));
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        signal.clear();
        Sensor sensor;
        SensorData sensorData;
        for (int i = 0; i < selectedPlot.getSensors().size(); i++)
        {
            sensor = selectedPlot.getSensors().get(i);
            if (sensor.getSensorType() == SensorType.SOIL_TEMPERATURE)
            {
                for (int j = 0; j < sensor.getSensorDataList().size(); j++)
                {
                    sensorData = sensor.getSensorDataList().get(j);
                    signal.add(j, sensorData.getDataRepresentationType());
                }
            }
        }

        f.add(p, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }//GEN-LAST:event_btnSoilTempChartActionPerformed

    private void btnAcidityChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcidityChartActionPerformed
        XYSeries signal = new XYSeries("amount");
        XYSeriesCollection dataset = new XYSeriesCollection(signal);

        JFrame f = new JFrame(selectedPlot.getName() + " soil acidity sensor chart");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFreeChart chart = ChartFactory.createXYLineChart(selectedPlot.getName(), "Reading number", "Reading", dataset);
        f.add(new ChartPanel(chart));
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        signal.clear();
        Sensor sensor;
        SensorData sensorData;
        for (int i = 0; i < selectedPlot.getSensors().size(); i++)
        {
            sensor = selectedPlot.getSensors().get(i);
            if (sensor.getSensorType() == SensorType.ACIDITY)
            {
                for (int j = 0; j < sensor.getSensorDataList().size(); j++)
                {
                    sensorData = sensor.getSensorDataList().get(j);
                    signal.add(j, sensorData.getDataRepresentationType());
                }
            }
        }

        f.add(p, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }//GEN-LAST:event_btnAcidityChartActionPerformed

    private void btnLightChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLightChartActionPerformed
        XYSeries signal = new XYSeries("amount");
        XYSeriesCollection dataset = new XYSeriesCollection(signal);

        JFrame f = new JFrame(selectedPlot.getName() + " light sensor chart");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFreeChart chart = ChartFactory.createXYLineChart(selectedPlot.getName(), "Reading number", "Reading", dataset);
        f.add(new ChartPanel(chart));
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        signal.clear();
        Sensor sensor;
        SensorData sensorData;
        for (int i = 0; i < selectedPlot.getSensors().size(); i++)
        {
            sensor = selectedPlot.getSensors().get(i);
            if (sensor.getSensorType() == SensorType.LIGHT_SENSOR)
            {
                for (int j = 0; j < sensor.getSensorDataList().size(); j++)
                {
                    sensorData = sensor.getSensorDataList().get(j);
                    signal.add(j, sensorData.getDataRepresentationType());
                }
            }
        }

        f.add(p, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }//GEN-LAST:event_btnLightChartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList acidityDataList;
    private javax.swing.JDialog addFarmDialog;
    private javax.swing.JDialog addFieldDialog;
    private javax.swing.JDialog addPlotDialog;
    private javax.swing.JList airTempDataList;
    private javax.swing.JButton btnAcidityChart;
    private javax.swing.JButton btnAddFarm;
    private javax.swing.JButton btnAddFarmCoords;
    private javax.swing.JButton btnAddField;
    private javax.swing.JButton btnAddFieldCoords;
    private javax.swing.JButton btnAddPlot;
    private javax.swing.JButton btnAddPlotCoords;
    private javax.swing.JButton btnAirTempChart;
    private javax.swing.JButton btnDeleteFarm;
    private javax.swing.JButton btnDeleteField;
    private javax.swing.JButton btnFarmDialogAdd;
    private javax.swing.JButton btnFarmDialogCancel;
    private javax.swing.JButton btnFieldDialogAdd;
    private javax.swing.JButton btnFieldDialogCancel;
    private javax.swing.JButton btnLightChart;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPlotDialogAdd;
    private javax.swing.JButton btnPlotDialogCancel;
    private javax.swing.JButton btnPressureChart;
    private javax.swing.JButton btnRemovePlot;
    private javax.swing.JButton btnSoilMoistureChart;
    private javax.swing.JButton btnSoilTempChart;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblFarmName;
    private javax.swing.JLabel lblFieldName;
    private javax.swing.JLabel lblFields;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList lightDataList;
    private javax.swing.JList<String> plotCoordList;
    private javax.swing.JList plotList;
    private javax.swing.JList pressureDataList;
    private javax.swing.JList soilMoistureDataList;
    private javax.swing.JList soilTempDataList;
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
