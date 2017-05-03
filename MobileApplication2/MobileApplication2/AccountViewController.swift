//
//  AccountViewController.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 03/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import UIKit

class AccountViewController: UIViewController {
    @IBOutlet weak var txtForename: UITextField!
    @IBOutlet weak var txtSurname: UITextField!
    @IBOutlet weak var txtPhone: UITextField!
    @IBOutlet weak var txtPassword: UITextField!
    @IBOutlet weak var txtRetype: UITextField!
    @IBOutlet weak var txtCurrentPassword: UITextField!

    override func viewDidLoad() {
        super.viewDidLoad()
        txtForename.setBottomBorder()
        txtSurname.setBottomBorder()
        txtPhone.setBottomBorder()
        txtPassword.setBottomBorder()
        txtRetype.setBottomBorder()
        txtCurrentPassword.setBottomBorder()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

}

extension UITextField {
    func setBottomBorder() {
        self.borderStyle = .none
        self.layer.backgroundColor = UIColor.white.cgColor
        
        self.layer.masksToBounds = false
        self.layer.shadowColor = UIColor.gray.cgColor
        self.layer.shadowOffset = CGSize(width: 0.0, height: 1.0)
        self.layer.shadowOpacity = 1.0
        self.layer.shadowRadius = 0.0
    }
}
