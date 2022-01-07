/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.gradle.execute;

import org.netbeans.modules.gradle.api.execute.GradleCommandLine;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import org.netbeans.api.project.Project;
import org.netbeans.modules.gradle.actions.CustomActionRegistrationSupport;
import org.netbeans.modules.gradle.api.execute.GradleExecConfiguration;
import org.openide.text.CloneableEditorSupport;

/**
 *
 * @author Laszlo Kishalmi
 */
public class GradleExecutorOptionsPanel extends javax.swing.JPanel {

    final Project project;
    
    private GradleExecConfiguration execConfig;
    
    public GradleExecutorOptionsPanel() {
        this(null);
    }
    
    /**
     * Creates new form GradleExecutorOptionsPanel
     */
    public GradleExecutorOptionsPanel(Project project) {
        this.project = project;
        initComponents();
        EditorKit kit = CloneableEditorSupport.getEditorKit(GradleCliEditorKit.MIME_TYPE);
        epCLI.setEditorKit(kit);
        if (project != null) {
            epCLI.getDocument().putProperty(Document.StreamDescriptionProperty, project);
        } else {
            tfRememberAs.setEnabled(false);
            lbRememberAs.setEnabled(false);
        }
        epCLI.requestFocus();
    }

    public void setCommandLine(GradleCommandLine cmd, GradleExecConfiguration cfg) {
        this.execConfig = cfg;
        GradleCommandLine text = new GradleCommandLine(cmd);
        execOptions.setCommandLine(text);
        text.remove(ExecutionOptionsPanel.getCLIMask());
        epCLI.setText(String.join(" ", text.getSupportedCommandLine()));
    }

    public boolean rememberAs() {
        String txt = tfRememberAs.getText().trim();
        if (!txt.isEmpty() && (project != null)) {
            CustomActionRegistrationSupport support = new CustomActionRegistrationSupport(project);
            support.setActiveConfiguration(execConfig);
            support.registerCustomAction(txt, String.join(" ", getCommandLine().getFullCommandLine()));
            support.saveAndReportErrors();
            return true;
        }
        return false;
    }
    
    public GradleCommandLine getCommandLine() {
       GradleCommandLine ret = GradleCommandLine.combine(execOptions.getCommandLine(),
               new GradleCommandLine(epCLI.getText()));
       return ret;
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbRememberAs = new javax.swing.JLabel();
        lbTasks = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        execOptions = new org.netbeans.modules.gradle.execute.ExecutionOptionsPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        epCLI = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        taPreview = new javax.swing.JTextArea();
        tfRememberAs = new javax.swing.JTextField();

        lbRememberAs.setLabelFor(tfRememberAs);
        org.openide.awt.Mnemonics.setLocalizedText(lbRememberAs, org.openide.util.NbBundle.getMessage(GradleExecutorOptionsPanel.class, "GradleExecutorOptionsPanel.lbRememberAs.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lbTasks, org.openide.util.NbBundle.getMessage(GradleExecutorOptionsPanel.class, "GradleExecutorOptionsPanel.lbTasks.text")); // NOI18N

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane1.setName("Preview"); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jScrollPane3.setViewportView(epCLI);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(execOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(execOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(GradleExecutorOptionsPanel.class, "GradleExecutorOptionsPanel.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        taPreview.setEditable(false);
        taPreview.setColumns(20);
        taPreview.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        taPreview.setLineWrap(true);
        taPreview.setRows(5);
        taPreview.setWrapStyleWord(true);
        jScrollPane2.setViewportView(taPreview);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(GradleExecutorOptionsPanel.class, "GradleExecutorOptionsPanel.jScrollPane2.TabConstraints.tabTitle"), jScrollPane2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTasks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(425, 425, 425)
                        .addComponent(lbRememberAs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfRememberAs, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbTasks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRememberAs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRememberAs))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() > 0) {
            taPreview.setText(String.join(" ", getCommandLine().getSupportedCommandLine()));
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane epCLI;
    private org.netbeans.modules.gradle.execute.ExecutionOptionsPanel execOptions;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbRememberAs;
    private javax.swing.JLabel lbTasks;
    private javax.swing.JTextArea taPreview;
    private javax.swing.JTextField tfRememberAs;
    // End of variables declaration//GEN-END:variables
}
