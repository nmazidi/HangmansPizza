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
    
    public partial class TRANSACTION
    {
        public int TRANSACTION_ID { get; set; }
        public int ORDER_ID { get; set; }
        public string PAYMENT_METHOD { get; set; }
        public string DETAIL_1 { get; set; }
        public string DETAIL_2 { get; set; }
        public string DETAIL_3 { get; set; }
        public string DETAIL_4 { get; set; }
        public decimal TRANSACTION_VALUE { get; set; }
    }
}
