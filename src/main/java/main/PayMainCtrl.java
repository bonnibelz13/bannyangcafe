
package main;

/**
 *
 * @author baibu
 */
public class PayMainCtrl {
    PayMainUI view;
    PaymentChooseCtrl chooseCtrl;
    PaybyPromptCtrl promptCtrl;
    
    
    // Constructor and Method
   public PayMainCtrl() {

        initComponents();
    }
    
    public void initComponents() {
        this.view = new PayMainUI();
        this.chooseCtrl = new PaymentChooseCtrl(this);
        this.promptCtrl = new PaybyPromptCtrl(this);

        setView("PaymentChoose", "null");
        
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    public PaymentChooseCtrl getPaymentChooseCtrl() {
        return chooseCtrl;
    }
    public PaybyPromptCtrl getPaybyPromptCtrl(){
        return promptCtrl;
    }
    
    
    
    public void setView(String to, String from) {
        // PaymentChoose -> PaybyPrompt 
        if (to.equals("PaybyPrompt") && from.equals("PaymentChoose")) {
            System.out.println("In PaybyPrompt");

            view.setContentPane(promptCtrl.getPanel());
            view.revalidate();
            view.repaint();
            
        // Back to PaymentChoose
        } else if (to.equals("PaymentChoose")) {
            
            // PayMain -> PaymentChoose
            if (from.equals("null")) {
                System.out.println("PaymentChoose UI (Home)");
                view.remove(view.getPanel());
                
                
            // PaybyPrompt -> PaymentChoose
            } else if (from.equals("PaybyPrompt")) {
                view.remove(promptCtrl.getPanel());
                
            }
            
            view.setContentPane(chooseCtrl.getPanel());
            view.revalidate();
            view.repaint();
        }
    }

}
