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
    
    public partial class RIDER_ACTIVITY
    {
        public int SHIFT_ID { get; set; }
        public int RIDER_ID { get; set; }
        public Nullable<System.DateTime> SHIFT_START { get; set; }
        public Nullable<System.DateTime> SHIFT_END { get; set; }
        public string SHIFT_LENGTH { get; set; }
        public Nullable<decimal> WAGE_MULTIPLIER { get; set; }
        public string LOCATION { get; set; }
        public Nullable<short> DELIVERIES_MADE { get; set; }
        public string STATUS { get; set; }
    
        public virtual DELIVERY_RIDER DELIVERY_RIDER { get; set; }
    }
}
