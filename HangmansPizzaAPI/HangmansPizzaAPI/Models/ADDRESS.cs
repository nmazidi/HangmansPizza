//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace HangmansPizzaAPI.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class ADDRESS
    {
        public short ADDRESS_ID { get; set; }
        public int CUSTOMER_ID { get; set; }
        public string ADDRESS_LINE_1 { get; set; }
        public string ADDRESS_LINE_2 { get; set; }
        public string TOWN { get; set; }
        public string POSTCODE { get; set; }
    
        public virtual CUSTOMER CUSTOMER { get; set; }
    }
}
