//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace HangmansPizzaAPI
{
    using System;
    using System.Collections.Generic;
    
    public partial class DELIVERY
    {
        public int DELIVERY_ID { get; set; }
        public int ORDER_ID { get; set; }
        public System.DateTime DATE_DELIVERED { get; set; }
        public string DELIVERY_NOTES { get; set; }
        public int RIDER_ID { get; set; }
        public string DELIVERY_STATUS { get; set; }
    
        public virtual ORDER ORDER { get; set; }
    }
}
